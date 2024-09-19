package uz.turgunboyevjurabek.muslimapp.feature.presentation.View.components.shimmers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import uz.turgunboyevjurabek.muslimapp.core.utils.shimmerEffect
import uz.turgunboyevjurabek.muslimapp.core.utils.shimmerEffectAsCard

@Composable
fun DayOf7ShimmerList(modifier: Modifier = Modifier) {
    Column {
        for (i in 0 .. 6){
            Card(
                modifier= Modifier
                    .height(100.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
            ) {
                ConstraintLayout(
                    modifier = Modifier
                        .fillMaxSize()
                        .shimmerEffectAsCard()
                ) {
                    val (region, date, weekday, hijriDate) = createRefs()
                    Text(
                        text = "                     ",
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 15.sp,
                        modifier = Modifier
                            .height(20.dp)
                            .shimmerEffect()
                            .height(20.dp)
                            .constrainAs(region) {
                                top.linkTo(parent.top, margin = 10.dp)
                                start.linkTo(parent.start, margin = 10.dp)

                            }
                    )
                    Text(
                        text = "                                    ",
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 15.sp,
                        modifier = Modifier
                            .shimmerEffect()
                            .height(20.dp)
                            .constrainAs(hijriDate) {
                                bottom.linkTo(parent.bottom, margin = 10.dp)
                                start.linkTo(parent.start, margin = 10.dp)
                            }
                    )
                    Text(
                        text = "                   ",
                        fontFamily = FontFamily.Serif,
                        fontSize = 15.sp,
                        modifier = Modifier
                            .shimmerEffect()
                            .height(20.dp)
                            .constrainAs(weekday) {
                                top.linkTo(parent.top, margin = 10.dp)
                                end.linkTo(parent.end, margin = 10.dp)
                            }
                    )
                    Text(
                        text = "                      ",
                        fontFamily = FontFamily.Serif,
                        fontSize = 14.sp,
                        modifier = Modifier
                            .shimmerEffect()
                            .height(20.dp)
                            .constrainAs(date) {
                                bottom.linkTo(parent.bottom, margin = 10.dp)
                                end.linkTo(parent.end, margin = 10.dp)
                            }
                    )
                }

            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }

}