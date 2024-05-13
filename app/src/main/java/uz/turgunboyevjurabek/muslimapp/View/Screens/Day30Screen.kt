package uz.turgunboyevjurabek.muslimapp.View.Screens

import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch
import uz.turgunboyevjurabek.muslimapp.Model.madels.birHaftalik.BirhaftalikItem
import uz.turgunboyevjurabek.muslimapp.Model.madels.birOylk.BirOylik
import uz.turgunboyevjurabek.muslimapp.Model.madels.birOylk.BirOylikItem
import uz.turgunboyevjurabek.muslimapp.Model.utils.DateUtil
import uz.turgunboyevjurabek.muslimapp.Model.utils.Status
import uz.turgunboyevjurabek.muslimapp.Model.utils.Status.*
import uz.turgunboyevjurabek.muslimapp.R
import uz.turgunboyevjurabek.muslimapp.View.Calendar.CalendarWidget
import uz.turgunboyevjurabek.muslimapp.ViewModel.Biroylik.BirOylikLogika
import uz.turgunboyevjurabek.muslimapp.ViewModel.Calendar.CalendarViewModel
import java.time.LocalDate
import java.util.Locale


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DayOf30Screen() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        val context = LocalContext.current
        val scope = rememberCoroutineScope()
        val viewModel = hiltViewModel<BirOylikLogika>()
        val monthList  = remember { mutableStateListOf<BirOylik>() }
        var selectDay=remember{ mutableStateOf(BirOylikItem()) }
        LaunchedEffect(key1 = true) {
            viewModel.getMontData().observeForever {
                when (it.status) {
                    LOADING -> {
                    }
                    ERROR -> {
                        Toast.makeText(context, "${it.message}", Toast.LENGTH_SHORT).show()
                    }
                    SUCCESS -> {
                        monthList.add(it.data!!)
                        selectDay.value=monthList[0][LocalDate.now().dayOfMonth-1]
                    }
                }
            }
        }
        CalendarView(selectDay,monthList)
        SelectDay(selectDay)
    }
}
@RequiresApi(Build.VERSION_CODES.O)
@Preview(showSystemUi = true)
@Composable
private fun UI30() {
    DayOf30Screen()
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SelectDay(selectDay: MutableState<BirOylikItem>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = selectDay.value.day.toString(), fontWeight = FontWeight.ExtraBold, fontSize = 24.sp)
        Text(text = "-", fontWeight = FontWeight.ExtraBold, fontSize = 24.sp)
        Text(text = LocalDate.now().month.name.lowercase(Locale.ROOT), fontWeight = FontWeight.ExtraBold, fontSize = 24.sp,)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
            .clip(RoundedCornerShape(topStart = 80.dp, bottomEnd = 80.dp))
            .wrapContentHeight(unbounded = true)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    text = "Bomdod",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = selectDay.value.times?.tongSaharlik.toString(),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    text = "Peshin ",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = selectDay.value.times?.peshin.toString(),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    text = "Asr      ",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = selectDay.value.times?.asr.toString(),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    text = "Shom ",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = selectDay.value.times?.shomIftor.toString(),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    text = "Hufton",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = selectDay.value.times?.hufton.toString(),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarView(
    selectDay: MutableState<BirOylikItem>,
    snapShotStaList: SnapshotStateList<BirOylik>,
    viewModel: CalendarViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .padding(vertical = 10.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = 80.dp, bottomEnd = 80.dp))
            .wrapContentHeight(unbounded = true)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
        ) {
            CalendarWidget(
                days = DateUtil.daysOfWeek,
                yearMonth = uiState.yearMonth,
                dates = uiState.dates,
                onPreviousMonthButtonClicked = { prevMonth ->
                    viewModel.toPreviousMonth(prevMonth)
                },
                onNextMonthButtonClicked = { nextMonth ->
                    viewModel.toNextMonth(nextMonth)
                },
                onDateClickListener = {
                    val select= snapShotStaList[0]
                    val index= if(it.dayOfMonth != "" ){
                        it.dayOfMonth.toInt()-1
                    } else {
                        LocalDate.now().dayOfMonth-1
                    }

                    selectDay.value =select[index.toString().toInt()]
                }
            )
        }
    }
}
