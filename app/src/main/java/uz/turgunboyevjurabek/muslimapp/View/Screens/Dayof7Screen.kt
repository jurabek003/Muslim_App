package uz.turgunboyevjurabek.muslimapp.View.Screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import uz.turgunboyevjurabek.muslimapp.Model.madels.birHaftalik.Birhaftalik
import uz.turgunboyevjurabek.muslimapp.Model.madels.birHaftalik.BirhaftalikItem
import uz.turgunboyevjurabek.muslimapp.Model.utils.Status
import uz.turgunboyevjurabek.muslimapp.ViewModel.Birhaftalik.BirHaftalikLogika

@Composable
fun Dayof7Screen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val weekViewModel = hiltViewModel<BirHaftalikLogika>()
        val context = LocalContext.current
        var weekData by remember {
            mutableStateOf(Birhaftalik())
        }

        LaunchedEffect(key1 = true) {
            weekViewModel.getWeekData().observeForever { result ->
                when (result.status) {
                    Status.LOADING -> {

                    }

                    Status.ERROR -> {
                        Toast.makeText(context, result.message, Toast.LENGTH_SHORT).show()
                    }

                    Status.SUCCESS -> {
                        weekData = result.data!!
                    }
                }
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ){
            items(weekData.size){
                ListUi(birhaftalikItem = weekData[it])
            }
        }


    }

}

@Composable
fun ListUi(birhaftalikItem: BirhaftalikItem) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .height(intrinsicSize = IntrinsicSize.Max),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 20.dp,
            disabledElevation = 20.dp,
            draggedElevation = 20.dp,
            hoveredElevation = 20.dp,
            pressedElevation = 20.dp,
            focusedElevation = 20.dp,
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(Modifier
                    .padding(10.dp),) {
                    Text(
                        text = birhaftalikItem.region.toString(),
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 15.sp,
                        modifier = Modifier
                            .padding(3.dp)
                    )
                    Text(
                        text = "Hijri ${birhaftalikItem.hijriDate?.month.toString()} oyi",
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 15.sp,
                        modifier = Modifier
                            .padding(3.dp)
                    )
                }

                Column(
                    Modifier.padding(10.dp),
                ) {
                    Text(
                        text = birhaftalikItem.weekday.toString(),
                        fontFamily = FontFamily.Serif,
                        fontSize = 15.sp,
                        modifier = Modifier
                            .padding(3.dp)
                    )
                    Text(
                        text = birhaftalikItem.date.toString().substring(0..9),
                        fontFamily = FontFamily.Serif,

                        fontSize = 14.sp,
                        modifier = Modifier
                            .padding(3.dp)
                    )
                }

            }
            Divider(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                thickness = 2.5.dp,

            )
            Column(
                Modifier.padding(10.dp),
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Bomdod",
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 17.sp,
                            modifier = Modifier
                                .padding(3.dp)
                        )
                    }
                    Column {
                        Text(
                            text = "Saharlik - ${birhaftalikItem.times?.tongSaharlik} da",
                            fontFamily = FontFamily.Serif,
                            fontSize = 14.sp,
                            modifier = Modifier
                                .padding(3.dp)
                        )
                        Text(
                            text = "Quyosh - ${birhaftalikItem.times?.quyosh} da",
                            fontFamily = FontFamily.Serif,
                            fontSize = 14.sp,
                            modifier = Modifier
                                .padding(3.dp)
                        )
                    }
                }
                Divider(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 5.dp, vertical = 5.dp),
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Peshin",
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 17.sp,
                            modifier = Modifier
                                .padding(3.dp)
                        )
                    }
                    Column {
                        Text(
                            text = "${birhaftalikItem.times?.peshin} da",
                            fontFamily = FontFamily.Serif,
                            fontSize = 14.sp,
                            modifier = Modifier
                                .padding(3.dp)
                        )
                    }
                }
                Divider(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 5.dp, vertical = 5.dp),
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Asr",
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 17.sp,
                            modifier = Modifier
                                .padding(3.dp)
                        )
                    }
                    Column {
                        Text(
                            text = "${birhaftalikItem.times?.asr} da",
                            fontFamily = FontFamily.Serif,
                            fontSize = 14.sp,
                            modifier = Modifier
                                .padding(3.dp)
                        )
                    }
                }
                Divider(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 5.dp, vertical = 5.dp),
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Shom",
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 17.sp,
                            modifier = Modifier
                                .padding(3.dp)
                        )
                    }
                    Column {
                        Text(
                            text = "Iftorlik - ${birhaftalikItem.times?.shomIftor} da",
                            fontFamily = FontFamily.Serif,
                            fontSize = 14.sp,
                            modifier = Modifier
                                .padding(3.dp)
                        )
                    }
                }
                Divider(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 5.dp, vertical = 5.dp),
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Hufton",
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 17.sp,
                            modifier = Modifier
                                .padding(3.dp)
                        )
                    }
                    Column {
                        Text(
                            text = "${birhaftalikItem.times?.hufton} da",
                            fontFamily = FontFamily.Serif,
                            fontSize = 14.sp,
                            modifier = Modifier
                                .padding(3.dp)
                        )
                    }
                }

            }
        }
    }

}

@Preview(showSystemUi = true)
@Composable
fun UI7Hafta() {
//    Dayof7Screen(navController = NavController(LocalContext.current))
//    ListUi(birhaftalikItem = BirhaftalikItem())


}