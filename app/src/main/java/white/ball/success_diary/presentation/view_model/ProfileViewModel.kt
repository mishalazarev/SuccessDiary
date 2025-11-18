package white.ball.success_diary.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import white.ball.domain.collection.achievement.AchievementCollection
import white.ball.domain.model.Achievement
import white.ball.domain.model.additional.AchievementTask
import white.ball.domain.use_case.model.AchievementUseCases
import white.ball.domain.use_case.model.CoffeeCoinUseCases
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val achievementUseCases: AchievementUseCases,
    private val coffeeCoinUseCases: CoffeeCoinUseCases,
) : ViewModel() {

    private val achievementCollection = AchievementCollection()

    private val _achievementList = MutableStateFlow<List<Achievement>>(emptyList())
    val achievementList: Flow<List<Achievement>> = _achievementList

    private val _isGetReward = MutableStateFlow(false)
    val isGetReward: Flow<Boolean> = _isGetReward

    init {
        viewModelScope.launch(Dispatchers.IO) {
            achievementUseCases.getAchievementListUseCase().collect { dbList ->
                if (dbList.isNullOrEmpty()) {
                    val source = achievementCollection.achievements

                    val insertedIds = mutableListOf<Long>()
                    for (ach in source) {
                        val newId =
                            achievementUseCases.insertAchievementUseCase(ach) // returns Long
                        insertedIds.add(newId)
                    }

                    val allTasks = mutableListOf<AchievementTask>()
                    for ((index, ach) in source.withIndex()) {
                        val realAchievementId = insertedIds[index]
                        ach.achievementTaskList.forEach { task ->
                            allTasks.add(task.copy(achievementId = realAchievementId))
                        }
                    }

                    achievementUseCases.insertAchievementTaskListUseCase(allTasks)
                    _achievementList.value =
                        source.mapIndexed { i, a -> a.copy(achievementId = insertedIds[i]) }

                } else {
                    _achievementList.value = dbList
                }
            }
        }
    }

    suspend fun updateAchievement(achievement: Achievement) {
        achievementUseCases.insertAchievementUseCase(achievement)
    }

    suspend fun addAchievementTaskList(taskList: List<AchievementTask>) {
        achievementUseCases.insertAchievementTaskListUseCase(taskList)
    }

    suspend fun getReward() {
        var reward = 0

        val updatedAchievementList = _achievementList.value.map { achievement ->
            val updatedTaskList = achievement.achievementTaskList.map { task ->
                if (achievement.current >= task.title.toInt() && !task.isCompleted) {
                    val updatedTask = task.copy(isCompleted = true)
                    reward += updatedTask.reward
                    updatedTask
                } else {
                    task
                }
            }

            achievement.copy(achievementTaskList = updatedTaskList)
        }

        _achievementList.value = updatedAchievementList

        val flatTaskList = updatedAchievementList.flatMap { it.achievementTaskList }
        achievementUseCases.insertAchievementTaskListUseCase(flatTaskList)
        coffeeCoinUseCases.updateBalanceUseCase(reward)
    }

    fun updateReward() {
        _isGetReward.value = _achievementList.value
            .any { achievement ->
                achievement.achievementTaskList.any { task ->
                    achievement.current >= task.title.toInt()
                }
            }
    }
}