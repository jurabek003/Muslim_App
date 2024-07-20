package uz.turgunboyevjurabek.muslimapp.View.Screens

import android.annotation.SuppressLint
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.Morph
import androidx.graphics.shapes.RoundedPolygon
import androidx.graphics.shapes.circle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import uz.turgunboyevjurabek.muslimapp.View.shape.MorphPolygonShape
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TasbexScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                ShapeCount()
            }
        }
    }
}

@Composable
fun ShapeCount() {
    val shapeA = remember{
        RoundedPolygon(
            12,
            rounding = CornerRounding(0.2f)
        )
    }
    val shapeB = remember{
        RoundedPolygon.circle(
            15
        )
    }
    val morph = remember{
        Morph(shapeA, shapeB)
    }
    val interactionSource = remember {
        MutableInteractionSource()
    }
    /**
     * For color anim code
     */
    val infiniteTransition = rememberInfiniteTransition(label = "")
     val color by infiniteTransition.animateColor(
         initialValue = Color.Transparent,
         targetValue = Color.Cyan,
         animationSpec = infiniteRepeatable(
             tween(durationMillis = 10000),
             repeatMode = RepeatMode.Reverse
         ), label = "allambalo"
     )
    val isPressed by interactionSource.collectIsPressedAsState()
    val animatedProgress = animateFloatAsState(
        targetValue = if (isPressed) 1f else 0f,
        label = "progress",
        animationSpec = spring(dampingRatio = 0.4f, stiffness = Spring.StiffnessMedium)
    )
    ElevatedCard(
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 30.dp,
            pressedElevation = 30.dp,
            focusedElevation = 50.dp
        ),
        modifier = Modifier
            .size(350.dp)
            .padding(8.dp)
            .clip(MorphPolygonShape(morph, animatedProgress.value))
            .size(200.dp)
            .clickable(interactionSource = interactionSource, indication = null) {}
    ) {
        Column(
            modifier = Modifier
            .fillMaxSize()
                .background(color),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                "Zikr",
                fontSize = 25.sp,
            )
        }
    }

}

@Preview(showSystemUi = true)
@Composable
fun TasbexUI() {
    val navController = rememberNavController()
    TasbexScreen(navController = navController)
}