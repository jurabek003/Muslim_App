package uz.turgunboyevjurabek.muslimapp.core.utils

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize

fun Modifier.shimmerEffectAsCard():Modifier=composed {
    var size by remember{
        mutableStateOf(IntSize.Zero)
    }
    val transition= rememberInfiniteTransition(label = "")
    val startOffsetX by transition.animateFloat(
        initialValue = -2*size.width.toFloat(),
        targetValue = 2*size.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(1200)
        ), label = ""
    )

    background(
        brush = Brush.linearGradient(
            colors = listOf(
               MaterialTheme.colorScheme.tertiaryContainer,
               MaterialTheme.colorScheme.onTertiary,
               MaterialTheme.colorScheme.tertiaryContainer,
            ),
            start = Offset(startOffsetX,0f),
            end = Offset(startOffsetX+size.width.toFloat(),size.height.toFloat())
        )
    ).onGloballyPositioned {
        size=it.size
    }
    
}
