package uz.turgunboyevjurabek.muslimapp.feature.presentation.View.Screens

import SelectRegionViewModel
import android.annotation.SuppressLint
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.StartOffset
import androidx.compose.animation.core.StartOffsetType
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.Morph
import androidx.graphics.shapes.RoundedPolygon
import androidx.graphics.shapes.circle
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay

import uz.turgunboyevjurabek.muslimapp.feature.domein.madels.bugungilik.Bugungi
import uz.turgunboyevjurabek.muslimapp.core.utils.DateUtil
import uz.turgunboyevjurabek.muslimapp.core.utils.Status
import uz.turgunboyevjurabek.muslimapp.R
import uz.turgunboyevjurabek.muslimapp.core.utils.coloredShadow
import uz.turgunboyevjurabek.muslimapp.feature.presentation.View.components.TimeListCard
import uz.turgunboyevjurabek.muslimapp.feature.presentation.View.components.shimmers.MainCardShimmer
import uz.turgunboyevjurabek.muslimapp.feature.presentation.View.components.shimmers.TimeListCardShimmer
import uz.turgunboyevjurabek.muslimapp.feature.presentation.View.shape.MorphPolygonShape
import uz.turgunboyevjurabek.muslimapp.feature.presentation.ViewModel.Bugungilik.BugungilkLogika
import java.text.SimpleDateFormat
import java.util.Date

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "SimpleDateFormat", "RememberReturnType")
@Composable
fun MainScreen(
    navController: NavHostController,
    selectRegionViewModel: SelectRegionViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val todayViewModel= hiltViewModel<BugungilkLogika>()
        val time= SimpleDateFormat("HH").format(Date())

        // Region o'zgarishini kuzatish
        val region by selectRegionViewModel.region.collectAsState()

        var isLoading by remember {
            mutableStateOf(true)
        }
        val context = LocalContext.current
        var todayData by remember {
            mutableStateOf(Bugungi())
        }
        LaunchedEffect(region) {
            todayViewModel.todayApi(region).observeForever { result ->
                when (result.status) {
                    Status.LOADING -> {}

                    Status.ERROR -> {
                        Toast.makeText(context, result.message, Toast.LENGTH_SHORT).show()
                    }
                    Status.SUCCESS -> {
                        todayData= result.data!!
                        isLoading=false
                    }
                }
            }
        }
        val color=if (isSystemInDarkTheme()) Color.White else Color.Black
        val elevatedCardShadowColor=if (isSystemInDarkTheme()) Color.White else Color.Black
        val infiniteTransition = rememberInfiniteTransition(label = "")
//         val color by infiniteTransition.animateColor(
//             initialValue = if (isSystemInDarkTheme()) Color.Green else Color.Black,
//             targetValue = if (isSystemInDarkTheme()) Color.Blue else Color.White,
//             animationSpec = infiniteRepeatable(
//                 tween(durationMillis = 2000),
//                 repeatMode = RepeatMode.Reverse
//             ), label = "allambalo"
//         )
//        val offsetX  by infiniteTransition.animateFloat(
//            initialValue = 10f,
//            targetValue = -10f,
//            animationSpec = infiniteRepeatable(
//                tween(durationMillis = 2000),
//                repeatMode = RepeatMode.Reverse
//            ),
//            label = ""
//        )

        /**
         * vaqti yaqin namoz
         */
        if (isLoading){
            MainCardShimmer()
        }else{
            ElevatedCard(
                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp, top = 25.dp)
                    .zIndex(1f)
                    .coloredShadow(
                        color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                        alpha = 0.5f,
                        borderRadius = 30.dp,
                        shadowRadius = 30.dp,
                        offsetX = 10.dp,
                        offsetY = 10.dp
                    )
                    .height(250.dp),
                shape = RoundedCornerShape(25.dp),
                elevation = CardDefaults.cardElevation(10.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .paint(
                            painter = when (time.toInt()) {
                                in 1..5 -> {
                                    painterResource(id = R.drawable.img_1)
                                }

                                in 8..14 -> {
                                    painterResource(id = R.drawable.img_2)
                                }

                                in 14..17 -> {
                                    painterResource(id = R.drawable.img_3)
                                }

                                in 17..20 -> {
                                    painterResource(id = R.drawable.img_4)
                                }

                                in 20..22 -> {
                                    painterResource(id = R.drawable.img_5)
                                }

                                else -> {
                                    painterResource(id = R.drawable.img_1)
                                }
                            },
                            contentScale = ContentScale.FillBounds
                        ),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Column {
                        Text(
                            text = when(time.toInt()){
                                in 1..5->{
                                    "Bomdod"
                                }
                                in 8..14->{
                                    "Peshin"
                                }
                                in 14..16->{
                                    "Asr"
                                }
                                in 17..19 ->{
                                    "Shom"
                                }
                                in 20..22 ->{
                                    "Hufton"
                                }
                                else->{
                                    "Bomdod"
                                } },
                            fontSize = 40.sp,
                            fontFamily = FontFamily.Serif,
                            color = Color.White,
                            modifier = Modifier.padding(start = 20.dp)
                        )

                        Text(
                            text = todayData?.date.toString(),
                            fontSize = 20.sp,
                            fontFamily = FontFamily.Serif,
                            color = Color.White,
                            modifier = Modifier.padding(start = 20.dp, top = 25.dp)
                        )
                        Text(
                            text = when(time.toInt()){
                                in 5..8->{
                                    todayData.times?.quyosh.toString()
                                }
                                in 8..13->{
                                    todayData.times?.peshin.toString()
                                }
                                in 14..16->{
                                    todayData.times?.asr.toString()
                                }
                                in 17..19 ->{
                                    todayData.times?.shomIftor.toString()
                                }
                                in 20..23 ->{
                                    todayData.times?.hufton.toString()
                                }
                                else->{
                                    todayData.times?.tongSaharlik.toString()
                                } },
                            fontSize = 30.sp,
                            fontFamily = FontFamily.Serif,
                            color = Color.White,
                            modifier = Modifier.padding(start = 20.dp, top = 10.dp)
                        )
                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = todayData.region.toString(),
                            fontSize = 25.sp,
                            fontFamily = FontFamily.Serif,
                            color = Color.White,
                            modifier = Modifier.padding(end = 20.dp, top = 20.dp)
                        )
                        Text(
                            text = todayData.weekday.toString(),
                            fontSize = 20.sp,
                            fontFamily = FontFamily.Serif,
                            color = Color.White,
                            modifier = Modifier.padding(end = 20.dp, top = 10.dp)
                        )

                    }
                }
            }
        }

        /**
         * 5 vaqtlik
         */
        if (isLoading){
            TimeListCardShimmer()
        }else{
            TimeListCard(todayData = todayData)
        }

    }
}
@RequiresApi(Build.VERSION_CODES.O)
@Preview(showSystemUi = true)
@Composable
fun UiMain() {
//    MainScreen(navController = NavHostController(context = LocalContext.current))
}

@Composable
fun Shape() {

    val shapeA = remember {
        RoundedPolygon(
            6, rounding = CornerRounding(0.2f)
        )
    }
    val shapeB = remember {
        RoundedPolygon.circle(
            6,
//            rounding = CornerRounding(0.1f)
        )
    }
    val morph = remember {
        Morph(shapeA, shapeB)
    }
    val interactionSource = remember {
        MutableInteractionSource()
    }
    val isPressed by interactionSource.collectIsPressedAsState()
    val animatedProgress = animateFloatAsState(
        targetValue = if (isPressed) 1f else 0f,
        label = "progress",
        animationSpec = spring(dampingRatio = 0.4f, stiffness = Spring.StiffnessMedium)
    )
    Box(modifier = Modifier
        .size(200.dp)
        .padding(8.dp)
        .clip(MorphPolygonShape(morph, animatedProgress.value))
        .background(Color(0xFF80DEEA))
        .size(200.dp)
        .clickable(interactionSource = interactionSource, indication = null) {}
    ) {
        Text("Hello", modifier = Modifier.align(Alignment.Center))
    }
}