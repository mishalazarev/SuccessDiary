package white.ball.success_diary.presentation.screen.schedule

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import white.ball.domain.extension_model.DatePeriod
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor() : ViewModel() {

    private var _currentDateSchedule: MutableStateFlow<DatePeriod> = MutableStateFlow(
        DatePeriod.DAY)
    val currentDateSchedule: StateFlow<DatePeriod> = _currentDateSchedule

    private var _titleDatePeriod: MutableStateFlow<String> = MutableStateFlow("")
    val titleDatePeriod: StateFlow<String> = _titleDatePeriod

    init {
        upDateSchedulePeriod()
    }


    fun setCurrentSchedule(schedule: DatePeriod) {
        _currentDateSchedule.value = schedule
        upDateSchedulePeriod()
    }

    private fun upDateSchedulePeriod() {
        when (_currentDateSchedule.value) {
            DatePeriod.DAY -> {
                val day = LocalDate.now().dayOfWeek
                val russianDay = day.getDisplayName(TextStyle.FULL, Locale("ru"))
                _titleDatePeriod.value = russianDay
            }
            DatePeriod.WEEK -> {
                val day = LocalDate.now()
                val startOfWeek = day.minusDays(day.dayOfWeek.value.toLong() - 1)
                val endOfWeek = startOfWeek.plusDays(6)
                Log.d("TimerPicker", "${startOfWeek.dayOfMonth} - ${endOfWeek.dayOfMonth}")
                _titleDatePeriod.value = "${startOfWeek.dayOfMonth} - ${endOfWeek.dayOfMonth}"
            }
            DatePeriod.ALL_TIME -> {

            }
            else -> _titleDatePeriod.value = ""

        }
    }
}