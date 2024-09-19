package uz.turgunboyevjurabek.muslimapp.feature.presentation.View.Screens

import SelectRegionViewModel
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColor
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material3.HorizontalDivider
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
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import uz.turgunboyevjurabek.muslimapp.feature.domein.madels.birHaftalik.Birhaftalik
import uz.turgunboyevjurabek.muslimapp.feature.domein.madels.birHaftalik.BirhaftalikItem
import uz.turgunboyevjurabek.muslimapp.core.utils.Status
import uz.turgunboyevjurabek.muslimapp.feature.presentation.View.components.shimmers.DayOf7ShimmerList
import uz.turgunboyevjurabek.muslimapp.feature.presentation.ViewModel.Birhaftalik.BirHaftalikLogika

@Composable
fun Dayof7Screen(
    navController: NavController,
    selectRegionViewModel: SelectRegionViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val weekViewModel = hiltViewModel<BirHaftalikLogika>()
        val context = LocalContext.current

        var isLoading by remember {
            mutableStateOf(true)
        }
        var weekData by remember {
            mutableStateOf(Birhaftalik())
        }

        LaunchedEffect(key1 = true) {
            weekViewModel.getWeekData(selectRegionViewModel.region.value).observeForever { result ->
                when (result.status) {
                    Status.LOADING -> {}
                    Status.ERROR -> {
                        Toast.makeText(context, result.message, Toast.LENGTH_SHORT).show()
                    }
                    Status.SUCCESS -> {
                        weekData = result.data!!
                        isLoading=false
                    }
                }
            }
        }
        if (isLoading){
            DayOf7ShimmerList()
        }else{
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(weekData.size) {
                    ListUi(birhaftalikItem = weekData[it])
                }
            }
        }
    }
}

@Composable
fun ListUi(birhaftalikItem: BirhaftalikItem) {

    var expandedState by remember {
        mutableStateOf(false)
    }
    var sizeState by remember { mutableStateOf(133.dp) }

    val size by animateDpAsState(
        targetValue = sizeState,
        tween(
            durationMillis = 500
        ), label = ""
    )


    /**
     * For color anim code
     */
    val infiniteTransition = rememberInfiniteTransition(label = "")
    /* val color by infiniteTransition.animateColor(
         initialValue = Color.Transparent,
         targetValue = Color.DarkGray,
         animationSpec = infiniteRepeatable(
             tween(durationMillis = 5000),
             repeatMode = RepeatMode.Reverse
         ), label = "allambalo"
     )
     */

    ElevatedCard(
        modifier = Modifier
            .height(size)
            .fillMaxWidth()
            .padding(15.dp),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 15.dp,
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clickable {
                    expandedState = !expandedState
                    if (expandedState) {
                        sizeState += 300.dp
                    } else {
                        sizeState -= 300.dp
                    }
                }
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
            ) {
                val (region, date, weekday, hijriDate) = createRefs()
                Text(
                    text = birhaftalikItem.region.toString(),
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp,
                    modifier = Modifier
                        .constrainAs(region) {
                            top.linkTo(parent.top, margin = 10.dp)
                            start.linkTo(parent.start, margin = 10.dp)

                        }
                )
                Text(
                    text = "Hijri ${birhaftalikItem.hijriDate?.month.toString()} oyi",
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp,
                    modifier = Modifier
                        .constrainAs(hijriDate){
                            bottom.linkTo(parent.bottom, margin = 10.dp)
                            start.linkTo(parent.start, margin = 10.dp)
                        }
                )
                Text(
                    text = birhaftalikItem.weekday.toString(),
                    fontFamily = FontFamily.Serif,
                    fontSize = 15.sp,
                    modifier = Modifier
                        .constrainAs(weekday){
                            top.linkTo(parent.top, margin = 10.dp)
                            end.linkTo(parent.end, margin = 10.dp)
                        }
                )
                Text(
                    text = birhaftalikItem.date.toString().substring(0..9),
                    fontFamily = FontFamily.Serif,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .constrainAs(date){
                            bottom.linkTo(parent.bottom, margin = 10.dp)
                            end.linkTo(parent.end, margin = 10.dp)
                        }
                )
            }
            HorizontalDivider(
                Modifier
                    .fillMaxWidth(),
                thickness = 2.5.dp
            )
            AnimatedVisibility(
                visible = expandedState,
                // Kengayish animatsiyasi uchun asosiy toifalardan foydalanish.
                enter = expandHorizontally() + fadeIn(),
                // Qisqarish animatsiyasi uchun asosiy toifalardan foydalanish.
                exit = shrinkHorizontally() + fadeOut()
            ) {
                // Agar expandedState true bo'lsa, quyidagi matn elementini ko'rsatish.
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

}

@Preview(showSystemUi = true)
@Composable
fun UI7Hafta() {
//    Dayof7Screen(navController = NavController(LocalContext.current))
//    ListUi(birhaftalikItem = BirhaftalikItem())


}