package uz.turgunboyevjurabek.muslimapp.feature.presentation.View.Calendar

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.turgunboyevjurabek.muslimapp.feature.data.Calendar.CalendarUiState
import java.time.LocalDate


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Content(
    dates: List<CalendarUiState.Date>,
    onDateClickListener: (CalendarUiState.Date) -> Unit,

    ) {
    Column {
        var index = 0
        repeat(6) {
            if (index >= dates.size) return@repeat
            Row {
                repeat(7) {
                    val item = if (index < dates.size) dates[index] else CalendarUiState.Date.Empty

                    ContentItem(
                        date = item,
                        onClickListener = onDateClickListener,
                        modifier = Modifier
                            .weight(1f)
                            .size(35.dp),
                        alpaText = index > item.isToday
                    )
                    index++
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ContentItem(
    date: CalendarUiState.Date,
    onClickListener: (CalendarUiState.Date) -> Unit,
    modifier: Modifier = Modifier,
    alpaText: Boolean,

    ) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(
                color = if (date.isSelected) {
                    Color.Black
                } else {
                    Color.Transparent
                }
            )
            .clickable {
                onClickListener(date)
            }
    ) {
        Text(
            text = date.dayOfMonth,
            fontSize = 16.sp,
            color = if (isSystemInDarkTheme()) Color.White else {
                if (date.isSelected) Color.White else Color.Black
            },
            style = if (date.isSelected) MaterialTheme.typography.bodyLarge else MaterialTheme.typography.bodySmall,
            fontWeight = if (date.isSelected) FontWeight.Bold else FontWeight.Medium,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(5.dp)
                .alpha(alpha = if (alpaText) 1f else 0.5f)
        )
    }
}