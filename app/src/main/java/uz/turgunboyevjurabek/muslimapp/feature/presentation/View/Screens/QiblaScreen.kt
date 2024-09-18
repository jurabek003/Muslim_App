@file:OptIn(ExperimentalAnimationApi::class)

package uz.turgunboyevjurabek.muslimapp.feature.presentation.View.Screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import uz.turgunboyevjurabek.muslimapp.core.utils.drawableToBitmap
import uz.turgunboyevjurabek.muslimapp.core.utils.vibrateDevice
import uz.turgunboyevjurabek.muslimapp.R
import uz.turgunboyevjurabek.muslimapp.core.utils.coloredShadow
import uz.turgunboyevjurabek.qiblafinderexample.service.CompassSensorManager
import uz.turgunboyevjurabek.qiblafinderexample.service.MyLocationManager

@Composable
fun QiblaScreen(
    modifier: Modifier = Modifier,
    qiblaDirection: Float,
    currentDirection: Float,
    navController: NavController,
    compassSensorManager: CompassSensorManager,
    locationManager: MyLocationManager,

) {
    val context = LocalContext.current

//    if (qiblaDirection.toInt()==0){
//
//    }
    locationManager.getLastKnownLocation()

    val compassBgBitmap =
        remember { drawableToBitmap(context, R.drawable.compass3).asImageBitmap() }
    val qiblaIconBitmap =
        remember { drawableToBitmap(context, R.drawable.qiblaiconpoint).asImageBitmap() }
    val needleBitmap = remember { drawableToBitmap(context, R.drawable.needles).asImageBitmap() }
    val goldQaba = remember { drawableToBitmap(context, R.drawable.goldqaba).asImageBitmap() }

    val minTolerance = 3f // Adjusted tolerance range
    val maxTolerance = 3.5f // Adjusted tolerance range

    val directionDifference = qiblaDirection - currentDirection
    val normalizedDifference = (directionDifference + 360) % 360

    val isFacingQibla =
        ((normalizedDifference in 0.0..maxTolerance.toDouble()) || (normalizedDifference >= 360 - minTolerance && normalizedDifference <= 360))

    var hasVibrated by remember { mutableStateOf(false) }

    // Vibrate when facing Qibla and not already vibrated
    if (isFacingQibla && !hasVibrated) {
        vibrateDevice(context, 200)
        hasVibrated = true // Set to true to prevent continuous vibration
    } else if (!isFacingQibla) {
        hasVibrated = false // Reset when not facing Qibla
    }
    val (mainLayout)= createRefs()
    ConstraintLayout(
        modifier = Modifier
            .layoutId(mainLayout)
            .fillMaxSize()
    ) {
        val (cardText,composeBox,itemCompose)=createRefs()

        Card(
            modifier = modifier
                .constrainAs(cardText) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(top = 20.dp, start = 15.dp,end=15.dp)
                .coloredShadow(
                    Color.Green,
                    alpha = if(isFacingQibla) 0.5f else 0.1f,
                    borderRadius = 10.dp
                ),
            border = BorderStroke(
                1.dp, color = if(isFacingQibla) Color.Green else Color.Unspecified
            ),
            shape = MaterialTheme.shapes.large
        ) {
            AnimatedContent(
                targetState = qiblaDirection.toInt(),
                transitionSpec = {
                    fadeIn(animationSpec = tween(durationMillis = 300)) with fadeOut(animationSpec = tween(durationMillis = 300))
                }, label = ""
            ) { targetState ->
                Text(
                    text = if (targetState == 0) {
                        "Sizning mazilingiz aniqlanmoqda iltimos kutib turing :)"
                    } else {
                        "Qibla: $targetState°"
                    },
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    modifier = modifier
                        .padding(10.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Box(
            modifier=modifier
                .constrainAs(itemCompose){
                    bottom.linkTo(composeBox.top)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                }
        ) {
            if (isFacingQibla) {
                Icon(
                    modifier = Modifier.size(60.dp),
                    painter = painterResource(id = R.drawable.goldqaba),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            } else {
                Icon(
                    modifier = Modifier.size(30.dp),
                    painter = painterResource(id = R.drawable.arrowup),
                    contentDescription = null
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))
        Box(
            modifier = Modifier
                .fillMaxHeight(0.5f)
                .padding(top = 50.dp)
                .constrainAs(composeBox) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
        ) {
            // Canvas for compass
            Canvas(modifier = Modifier.fillMaxSize()) {
                val compassCenter = Offset(size.width / 2, size.height / 2)
                val compassRadius = size.minDimension / 2.5f

                // Rotate the entire compass background image
                rotate(degrees = -currentDirection, pivot = compassCenter) {
                    drawImage(
                        image = compassBgBitmap,
                        topLeft = Offset(
                            compassCenter.x - compassBgBitmap.width / 2,
                            compassCenter.y - compassBgBitmap.height / 2
                        )
                    )
                }
                // Draw the Qibla direction needle
                rotate(degrees = qiblaDirection - currentDirection, pivot = compassCenter) {
                    val needleStartY = compassCenter.y - needleBitmap.height / 1.1f
                    drawImage(
                        image = needleBitmap,
                        topLeft = Offset(
                            compassCenter.x - needleBitmap.width / 2f,
                            needleStartY
                        )
                    )

                    // Draw the Qibla icon
                    if (!isFacingQibla) {
                        drawImage(
                            image = qiblaIconBitmap,
                            topLeft = Offset(
                                compassCenter.x - qiblaIconBitmap.width / 2,
                                compassCenter.y - compassRadius - qiblaIconBitmap.height / 1.1f
                            ),
                        )
                    }
                }
            }
        }
    }
}
