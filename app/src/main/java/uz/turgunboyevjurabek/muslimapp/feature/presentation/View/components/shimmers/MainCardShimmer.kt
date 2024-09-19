package uz.turgunboyevjurabek.muslimapp.feature.presentation.View.components.shimmers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.turgunboyevjurabek.muslimapp.R
import uz.turgunboyevjurabek.muslimapp.core.utils.shimmerEffect
import uz.turgunboyevjurabek.muslimapp.core.utils.shimmerEffectAsCard

@Composable
fun MainCardShimmer(modifier: Modifier = Modifier) {
    ElevatedCard(
        modifier = Modifier
            .padding(start = 15.dp, end = 15.dp, top = 20.dp)
            .height(250.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer
        ),
        elevation = CardDefaults.cardElevation(5.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .shimmerEffectAsCard(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Box(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(start = 20.dp)
                        .shimmerEffect(),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = "            ",
                        fontSize = 40.sp,
                        fontFamily = FontFamily.Serif,
                        color = Color.White,
                        modifier = Modifier
                    )
                }
                Text(
                    text = "               ",
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Serif,
                    color = Color.White,
                    modifier = Modifier
                        .padding(start = 20.dp, top = 25.dp)
                        .shimmerEffect()
                )
                Text(
                    text = "         ",
                    fontSize = 30.sp,
                    fontFamily = FontFamily.Serif,
                    color = Color.White,
                    modifier = Modifier
                        .padding(start = 20.dp, top = 10.dp)
                        .shimmerEffect()
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "             ",
                    fontSize = 25.sp,
                    fontFamily = FontFamily.Serif,
                    color = Color.White,
                    modifier = Modifier
                        .padding(end = 20.dp, top = 20.dp)
                        .shimmerEffect()
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "           ",
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Serif,
                    color = Color.White,
                    modifier = Modifier
                        .padding(end = 20.dp, top = 10.dp)
                        .shimmerEffect()
                )
            }
        }
    }

}