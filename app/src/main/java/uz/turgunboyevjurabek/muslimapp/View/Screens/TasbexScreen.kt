package uz.turgunboyevjurabek.muslimapp.View.Screens

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.Morph
import androidx.graphics.shapes.RoundedPolygon
import androidx.graphics.shapes.circle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay
import uz.turgunboyevjurabek.muslimapp.MyAplication
import uz.turgunboyevjurabek.muslimapp.R
import uz.turgunboyevjurabek.muslimapp.View.UIutils.AnimatedCounter
import uz.turgunboyevjurabek.muslimapp.View.shape.MorphPolygonShape
import uz.turgunboyevjurabek.muslimapp.ViewModel.DataStorePreferencesViewModel.CounterViewModel
import uz.turgunboyevjurabek.muslimapp.core.utils.coloredShadow

@OptIn(ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TasbexScreen(navController: NavController, viewModel: CounterViewModel) {
    val counterViewModel by viewModel.counter.collectAsState()
    val context = LocalContext.current
    val constraintSet = ConstraintSet {
        createRefFor("AnimatedText")
        createRefFor("MessageText")
        createRefFor("RowIcons")
        createRefFor("ShapeCount")
    }

    var count by rememberSaveable {
        mutableIntStateOf(counterViewModel)
    }
    ConstraintLayout(
        modifier = Modifier.fillMaxSize(),
    ) {
        val (animatedText, messageText,columnItems, shapeCount) = createRefs()
        AnimatedCounter(
            modifier = Modifier
                .constrainAs(animatedText) {
                    top.linkTo(parent.top, margin=50.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            count = count, style = TextStyle(
                fontFamily = FontFamily.Serif, fontSize = 55.sp, fontWeight = FontWeight.ExtraBold
            )
        )
        MessageText(
            viewModel = viewModel,
            modifier = Modifier
                .constrainAs(messageText){
                    top.linkTo(animatedText.bottom)
                    start.linkTo(animatedText.start)
                    end.linkTo(animatedText.end)
                }
        )
        Column(
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
                .constrainAs(columnItems){
                    top.linkTo(animatedText.bottom, margin = 50.dp)
                }
        ) {
            Row(
                modifier = Modifier
                    .height(170.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {
                    Toast.makeText(context, "Story icon clicked", Toast.LENGTH_SHORT).show()
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_story),
                        contentDescription = "story icon"
                    )
                }
                Spacer(modifier = Modifier.width(50.dp))

                IconButton(onClick = {
                    Toast.makeText(context, "Restart icon clicked", Toast.LENGTH_SHORT).show()
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_restart),
                        contentDescription = "restart icon"
                    )
                }

            }

        }
        ShapeCount(
            onClick = {
                count++
                viewModel.incrementCounter()
            },
            count = count,
            modifier = Modifier
                .constrainAs(shapeCount){
                    bottom.linkTo(parent.bottom, margin = 30.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )
    }
}

@Composable
fun MessageText(viewModel: CounterViewModel,modifier: Modifier) {
    val counterViewModel = viewModel.counter.collectAsState()
    // Ushbu holat matnni boshqaradi
    var showText by remember { mutableStateOf(true) }

    // LaunchedEffect bilan 8 soniyadan keyin matnni yo'q qilamiz
    LaunchedEffect(Unit) {
        delay(8000L) // 8 soniya kutish
        showText = false
    }
    AnimatedVisibility(
        modifier = modifier,
        visible = showText, // showText true bo'lsa matn ko'rsatilad
        enter = fadeIn(animationSpec = tween(durationMillis = 1000)), // 1 soniyada paydo bo'lish animatsiyasi
        exit = slideOutHorizontally(
            targetOffsetX = { fullWidth -> fullWidth }, animationSpec = tween(durationMillis = 3000)
        )
    ) {
        Text(
            text = if (counterViewModel.value == 0) "Yangi zikr" else "Avvalgi zikrni davom ettiring",
            fontSize = 15.sp,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily.Serif,
            modifier = modifier
                .fillMaxWidth()
                .coloredShadow(
                    Color.Red,
                    borderRadius = 20.dp,
                    shadowRadius = 30.dp,
                )
        )
    }

}

@Composable
fun ShapeCount(
    onClick: () -> Unit, count: Int,
    modifier: Modifier
) {

    var alphaForShadow by rememberSaveable {
        mutableFloatStateOf(0.01f)
    }
    when (count) {
        in 0..9 -> alphaForShadow = 0.01f
        in 10..19 -> alphaForShadow = 0.05f
        in 20..29 -> alphaForShadow = 0.1f
        in 30..39 -> alphaForShadow = 0.15f
        in 40..49 -> alphaForShadow = 0.2f
        in 50..59 -> alphaForShadow = 0.25f
        in 60..69 -> alphaForShadow = 0.3f
        in 70..79 -> alphaForShadow = 0.35f
        in 80..89 -> alphaForShadow = 0.4f
        in 90..99 -> alphaForShadow = 0.45f
        else -> 0.5f
    }
    val shapeA = remember {
        RoundedPolygon(
            12, rounding = CornerRounding(0.2f)
        )
    }
    val shapeB = remember {
        RoundedPolygon.circle(
            15
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
    Card(
        modifier = modifier
            .coloredShadow(
                Color.Yellow,
                borderRadius = 150.dp,
                alpha = alphaForShadow,
                shadowRadius = 50.dp,
                offsetX = 0.dp,
                offsetY = 0.dp
            )
            .size(350.dp)
            .padding()
            .clip(MorphPolygonShape(morph, animatedProgress.value))
            .size(200.dp)
            .clickable(interactionSource = interactionSource, indication = null) {
                onClick()
            },
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                "Zikr",
                fontSize = 25.sp,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.ExtraBold
            )
        }

    }
}

@Preview(showSystemUi = true)
@Composable
fun TasbexUI() {
    val navController = rememberNavController()
//    val dataStore=(LocalContext.current as MyAplication).dataStore
//    TasbexScreen(navController = navController, CounterViewModel(dataStore = DataStore<Preferences>()))
}
/**
 * For color anim code
 */
//val infiniteTransition = rememberInfiniteTransition(label = "")
//val color by infiniteTransition.animateColor(
//    initialValue = Color.Transparent,
//    targetValue = Color.Cyan,
//    animationSpec = infiniteRepeatable(
//        tween(durationMillis = 10000),
//        repeatMode = RepeatMode.Reverse
//    ), label = "allambalo"
//)