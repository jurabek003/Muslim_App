package uz.turgunboyevjurabek.muslimapp.View.Screens

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.rotationMatrix
import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.Morph
import androidx.graphics.shapes.RoundedPolygon
import androidx.graphics.shapes.circle
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController

import uz.turgunboyevjurabek.muslimapp.Model.madels.bugungilik.Bugungi
import uz.turgunboyevjurabek.muslimapp.Model.madels.timeConverter.DateConverter
import uz.turgunboyevjurabek.muslimapp.Model.network.ApiService
import uz.turgunboyevjurabek.muslimapp.Model.repozitory.MyRepozitor
import uz.turgunboyevjurabek.muslimapp.Model.utils.DateUtil
import uz.turgunboyevjurabek.muslimapp.Model.utils.Status
import uz.turgunboyevjurabek.muslimapp.R
import uz.turgunboyevjurabek.muslimapp.View.navigation.Navigation
import uz.turgunboyevjurabek.muslimapp.View.shape.MorphPolygonShape
import uz.turgunboyevjurabek.muslimapp.View.shape.RoundedPolygonShape
import uz.turgunboyevjurabek.muslimapp.ViewModel.Bugungilik.BugungilkLogika
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.Date

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "SimpleDateFormat", "RememberReturnType")
@Composable
fun MainScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val todayViewModel= hiltViewModel<BugungilkLogika>()
        val context = LocalContext.current
        var todayData by remember {
            mutableStateOf(Bugungi())
        }

        Toast.makeText(context, "MM ${DateUtil.presentMonth}", Toast.LENGTH_SHORT).show()
        LaunchedEffect(key1 = true) {
            todayViewModel.todayApi().observeForever { result ->
                when (result.status) {
                    Status.LOADING -> {
                        Toast.makeText(context, result.message, Toast.LENGTH_SHORT).show()
                    }
                    Status.ERROR -> {
                        Toast.makeText(context, result.message, Toast.LENGTH_SHORT).show()
                    }
                    Status.SUCCESS -> {
                        todayData= result.data!!
                    }
                }
            }
        }
        /**
         * vaqti yaqin namoz
         */
        val time= SimpleDateFormat("HH").format(Date())
        ElevatedCard(
            modifier = Modifier
                .padding(start = 15.dp, end = 15.dp, top = 20.dp)
                .height(250.dp),
            shape = RoundedCornerShape(20.dp),
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
                            in 17..20 ->{
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
//        val scrollableState = rememberScrollableState(consumeScrollDelta = )
        
        /**
         * 5 vaqtlik
         */
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {

            /**
             * Bomdod
             */
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(start = 15.dp, end = 15.dp, top = 25.dp)
                    .clip(RoundedCornerShape(topEnd = 40.dp, bottomStart = 40.dp)),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        ,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                   Column(
                       modifier = Modifier
                           .padding(start = 10.dp),
                       horizontalAlignment = Alignment.CenterHorizontally,
                       verticalArrangement = Arrangement.Center
                   ) {
                       Text(text = "Bomdod", fontSize = 20.sp, fontWeight = FontWeight.ExtraBold)
                   }
                    Column(
                        modifier = Modifier
                            .padding(end=10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(text = "Saharlik  - ${todayData.times?.tongSaharlik}")
                        Text(text = "Quyosh    - ${todayData.times?.quyosh}")
                    }

                }
            }


            /**
             * Peshin
             */
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(start = 15.dp, end = 15.dp, top = 25.dp)
                    .clip(RoundedCornerShape(topEnd = 40.dp, bottomStart = 40.dp)),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        ,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier
                            .padding(start = 10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(text = "Peshin", fontSize = 20.sp, fontWeight = FontWeight.ExtraBold)
                    }
                    Column(
                        modifier = Modifier
                            .padding(end=10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(text = "Bugun - ${todayData.times?.peshin} da")
                    }

                }
            }


            /**
             * Asir
             */
             Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(start = 15.dp, end = 15.dp, top = 25.dp)
                    .clip(RoundedCornerShape(topEnd = 40.dp, bottomStart = 40.dp)),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        ,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier
                            .padding(start = 10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(text = "Asir", fontSize = 20.sp, fontWeight = FontWeight.ExtraBold)
                    }
                    Column(
                        modifier = Modifier
                            .padding(end=10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(text = "Bugun - ${todayData.times?.asr} da")
                    }

                }
            }


            /**
             * Shom
             */
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(start = 15.dp, end = 15.dp, top = 25.dp)
                    .clip(RoundedCornerShape(topEnd = 40.dp, bottomStart = 40.dp)),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        ,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier
                            .padding(start = 10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(text = "Shom", fontSize = 20.sp, fontWeight = FontWeight.ExtraBold)
                    }
                    Column(
                        modifier = Modifier
                            .padding(end=10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(text = "Iftor - ${todayData.times?.shomIftor} da")
                    }

                }
            }


            /**
             * Hufton
             */
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(start = 15.dp, end = 15.dp, top = 25.dp, bottom = 5.dp)
                    .clip(RoundedCornerShape(topEnd = 40.dp, bottomStart = 40.dp)),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        ,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier
                            .padding(start = 10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(text = "Hufton", fontSize = 20.sp, fontWeight = FontWeight.ExtraBold)
                    }
                    Column(
                        modifier = Modifier
                            .padding(end=10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(text = "Bugun - ${todayData.times?.hufton} da")
                    }

                }
            }
        }
    }
}
@RequiresApi(Build.VERSION_CODES.O)
@Preview(showSystemUi = true)
@Composable
fun UiMain() {
    MainScreen(navController = NavHostController(context = LocalContext.current))
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