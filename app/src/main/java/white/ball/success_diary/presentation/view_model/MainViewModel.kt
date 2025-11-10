package white.ball.success_diary.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import white.ball.domain.extension_model.TagStatus
import white.ball.domain.model.CoffeeCoin
import white.ball.domain.model.Tag
import white.ball.domain.use_case.model.CoffeeCoinUseCases
import white.ball.domain.use_case.model.TagUseCases
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val tagUseCases: TagUseCases,
    private val coffeeCoinUseCases: CoffeeCoinUseCases
) : ViewModel() {

    private val _coffeeCoins = MutableStateFlow<CoffeeCoin?>(null)
    val coffeeCoin: Flow<CoffeeCoin?> = _coffeeCoins

    val tags = tagUseCases.getTagListUseCase()

    private val _selectedNavigationBottomBarIndex = MutableStateFlow(0)

    val selectedNavigationBottomBarIndex: Flow<Int> = _selectedNavigationBottomBarIndex

    private val _isOpenTimer = MutableStateFlow(false)
    val isTimerRunning: Flow<Boolean> = _isOpenTimer

    private val _isOpenDialogBalance = MutableStateFlow(false)
    val isOpenDialogBalance: Flow<Boolean> = _isOpenDialogBalance

    private val _isOpenDialogTagCollection = MutableStateFlow(false)
    val isOpenDialogTagCollection: Flow<Boolean> = _isOpenDialogTagCollection

    init {
        viewModelScope.launch(Dispatchers.IO) {
            coffeeCoinUseCases.getCoffeeCoinsUseCase().collect { coffeeCoin ->
                if (coffeeCoin == null) {
                    coffeeCoinUseCases.createCoffeeCoinUseCase(CoffeeCoin())
                } else {
                    _coffeeCoins.value = coffeeCoin
                }
            }
        }
    }

    fun setTimer() {
        _isOpenTimer.value = !_isOpenTimer.value
    }

    fun setSelectedNavigationBottomBarIndex(index: Int) {
        _selectedNavigationBottomBarIndex.value = index
    }

    fun setDialogBalance() {
        _isOpenDialogBalance.value = !_isOpenDialogBalance.value
    }

    suspend fun addTag(tag: Tag) {
        tagUseCases.createTagUseCase(tag)
    }

    suspend fun updateBalance(balance: Int) {
        coffeeCoinUseCases.updateBalanceUseCase(balance)
    }

    suspend fun buyTag(tag: Tag): Boolean {
        _coffeeCoins.value?.let { coffeeCoin ->
            if (tag.price > coffeeCoin.balance) return false

            tag.status = TagStatus.AVAILABLE
            tagUseCases.updateTagUseCase(tag)
            return true
        }
        return false
    }
}