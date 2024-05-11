package uz.turgunboyevjurabek.muslimapp.Model.Calendar

import android.os.Build
import androidx.annotation.RequiresApi
import uz.turgunboyevjurabek.muslimapp.Model.utils.getDayOfMonthStartingFromMonday

import java.time.LocalDate
import java.time.YearMonth


class CalendarDataSource {

    @RequiresApi(Build.VERSION_CODES.O)
    fun getDates(yearMonth: YearMonth): List<CalendarUiState.Date> {
        return yearMonth.getDayOfMonthStartingFromMonday()
            .map { date ->
                CalendarUiState.Date(
                    dayOfMonth = if (date.monthValue == yearMonth.monthValue) {
                        "${date.dayOfMonth}"
                    } else {
                        "" // Fill with empty string for days outside the current month
                    },
                    isSelected = date.isEqual(LocalDate.now()) && date.monthValue == yearMonth.monthValue,
                    isToday = LocalDate.now().dayOfMonth  ,//date.isEqual(LocalDate.now())

                )
            }
    }
}