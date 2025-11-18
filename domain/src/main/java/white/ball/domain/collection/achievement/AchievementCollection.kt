package white.ball.domain.collection.achievement

import white.ball.domain.R
import white.ball.domain.extension_model.AchievementMeasurement
import white.ball.domain.model.Achievement
import white.ball.domain.model.additional.AchievementTask

class AchievementCollection() {

    private val _achievements = mutableListOf(

        Achievement(
            achievementId = 1,
            title = "Концентрация:",
            R.drawable.decor_profile_concentrate,
            measurement = AchievementMeasurement.DAY,
            current = 0,
            achievementTaskList = listOf(
                AchievementTask(
                    achievementId = 1,
                    achievementTaskId = 1,
                    title = "3",
                    reward = 25
                ),
                AchievementTask(
                    achievementId = 1,
                    achievementTaskId = 2,
                    title = "7",
                    reward = 50
                ),
                AchievementTask(
                    achievementId = 1,
                    achievementTaskId = 3,
                    title = "30",
                    reward = 100
                ),
            )
        ),

        Achievement(
            achievementId = 2,
            title = "Количество выполнения одного тега:",
            R.drawable.decor_profile_tag,
            measurement = AchievementMeasurement.ONE_TAG,
            current = 0,
            achievementTaskList = listOf(
                AchievementTask(
                    achievementId = 2,
                    achievementTaskId = 4,
                    title = "10",
                    reward = 25
                ),
                AchievementTask(
                    achievementId = 2,
                    achievementTaskId = 5,
                    title = "100",
                    reward = 50
                )
            )
        ),

        Achievement(
            achievementId = 3,
            title = "Количество выполнения всех тегов:",
            R.drawable.decor_profile_tag,
            measurement = AchievementMeasurement.COMMON_TAG,
            current = 0,
            achievementTaskList = listOf(
                AchievementTask(
                    achievementId = 3,
                    achievementTaskId = 6,
                    title = "3",
                    reward = 25
                ),
                AchievementTask(
                    achievementId = 3,
                    achievementTaskId = 7,
                    title = "7",
                    reward = 50
                ),
                AchievementTask(
                    achievementId = 3,
                    achievementTaskId = 8,
                    title = "30",
                    reward = 100
                )
            )
        ),

        Achievement(
            achievementId = 4,
            title = "Общая сумма часов концентрации:",
            R.drawable.decor_profile_concentrate,
            measurement = AchievementMeasurement.HOUR,
            current = 0,
            achievementTaskList = listOf(
                AchievementTask(
                    achievementId = 4,
                    achievementTaskId = 9,
                    title = "168",
                    reward = 25
                ),
                AchievementTask(
                    achievementId = 4,
                    achievementTaskId = 10,
                    title = "720",
                    reward = 50
                )
            )
        ),

        Achievement(
            achievementId = 5,
            title = "Вести дневник непрерывно:",
            R.drawable.decor_profile_note,
            measurement = AchievementMeasurement.NOTE,
            current = 0,
            achievementTaskList = listOf(
                AchievementTask(
                    achievementId = 5,
                    achievementTaskId = 11,
                    title = "7",
                    reward = 25
                ),
                AchievementTask(
                    achievementId = 5,
                    achievementTaskId = 12,
                    title = "30",
                    reward = 50
                )
            )
        ),

        Achievement(
            achievementId = 6,
            title = "Поделиться:",
            R.drawable.decor_profile_note,
            measurement = AchievementMeasurement.SHARE,
            current = 0,
            achievementTaskList = listOf(
                AchievementTask(
                    achievementId = 6,
                    achievementTaskId = 13,
                    title = "3",
                    reward = 100
                ),
            )
        ),

        Achievement(
            achievementId = 7,
            title = "Разблокировать тэг:",
            R.drawable.decor_profile_unlocked,
            measurement = AchievementMeasurement.BUY_TAG,
            current = 0,
            achievementTaskList = listOf(
                AchievementTask(
                    achievementId = 7,
                    achievementTaskId = 14,
                    title = "7",
                    reward = 100
                ),
                AchievementTask(
                    achievementId = 7,
                    achievementTaskId = 15,
                    title = "15",
                    reward = 100
                ),
            )
        ),
    )

    val achievements: List<Achievement> = _achievements

    fun addOneToAchievement(achievementList: List<Achievement>, measurement: AchievementMeasurement): List<Achievement> {
        achievementList
            .filter { it.measurement == measurement }
            .forEach { it.current += 1 }

        return achievementList
    }

    fun resetAchievement(achievementList: List<Achievement>, measurement: AchievementMeasurement): List<Achievement> {
        achievementList
            .filter { it.measurement == measurement }
            .forEach { it.current = 0 }

        return achievementList
    }
}