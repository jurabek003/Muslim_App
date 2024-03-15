package uz.turgunboyevjurabek.muslimapp.View.Screens

import android.annotation.SuppressLint
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.Morph
import androidx.graphics.shapes.RoundedPolygon
import androidx.graphics.shapes.circle
import androidx.navigation.NavController
import uz.turgunboyevjurabek.muslimapp.R
import uz.turgunboyevjurabek.muslimapp.View.ButtonNavigation.ButtonNavigation
import uz.turgunboyevjurabek.muslimapp.View.navigation.Navigation
import uz.turgunboyevjurabek.muslimapp.View.shape.MorphPolygonShape
import uz.turgunboyevjurabek.muslimapp.View.shape.RoundedPolygonShape

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Scaffold(
            modifier = Modifier,
            content = {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .navigationBarsPadding()
                ) {
                    val hexagon = remember {
                        RoundedPolygon(
                            5, rounding = CornerRounding(1.0f)
                        )
                    }
                    val clip = remember(hexagon) {
                        RoundedPolygonShape(polygon = hexagon)
                    }
                    ElevatedCard(
                        modifier = Modifier
                            .padding(10.dp)
                            .statusBarsPadding()
                            .height(250.dp),
                        shape = RoundedCornerShape(20.dp),
                        elevation = CardDefaults.cardElevation(30.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .paint(
                                    painter = painterResource(id = R.drawable.img_5),
                                    contentScale = ContentScale.FillBounds
                                ),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    text = "Peshin",
                                    fontSize = 40.sp,
                                    fontFamily = FontFamily.Serif,
                                    color = Color.White,
                                    modifier = Modifier.padding(start = 20.dp,)
                                )
                                Text(
                                    text = "12.12.1221",
                                    fontSize = 20.sp,
                                    fontFamily = FontFamily.Serif,
                                    color = Color.White,
                                    modifier = Modifier.padding(start = 20.dp, top = 10.dp)
                                )
                                Text(
                                    text = "12:45",
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
                                    text = "Farg'ona",
                                    fontSize = 25.sp,
                                    fontFamily = FontFamily.Serif,
                                    color = Color.White,
                                    modifier = Modifier.padding(end = 20.dp, top = 20.dp)
                                )
                                Text(
                                    text = "Dushanba",
                                    fontSize = 20.sp,
                                    fontFamily = FontFamily.Serif,
                                    color = Color.White,
                                    modifier = Modifier.padding(end = 20.dp, top = 10.dp)
                                )
                            }
                        }

                    }

                }

            },
            bottomBar = {
                ButtonNavigation(navController = navController)
            }
        )



    }

}

@Composable
fun BottomNavigation(function: () -> Unit) {
    val selectableIndex = remember{
        mutableIntStateOf(0)
    }

    
}

@Preview(showSystemUi = true)
@Composable
fun UiMain() {

    MainScreen(navController = NavController(context = LocalContext.current))

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
        .clickable(interactionSource = interactionSource, indication = null) {}) {
        Text("Hello", modifier = Modifier.align(Alignment.Center))
    }
}