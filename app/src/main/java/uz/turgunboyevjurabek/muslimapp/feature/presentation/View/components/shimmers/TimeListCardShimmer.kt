package uz.turgunboyevjurabek.muslimapp.feature.presentation.View.components.shimmers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.turgunboyevjurabek.muslimapp.core.utils.shimmerEffect
import uz.turgunboyevjurabek.muslimapp.core.utils.shimmerEffectAsCard

@Composable
fun TimeListCardShimmer(modifier: Modifier = Modifier) {

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
                    .shimmerEffectAsCard()
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .padding(start = 10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "           ",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier
                            .shimmerEffect()
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(end = 15.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "                                          ",
                        modifier = Modifier
                            .height(20.dp)
                            .shimmerEffect()
                    )
                    Spacer(modifier = Modifier.height(7.dp))
                    Text(
                        text = "                                          ",
                        modifier = Modifier
                            .height(20.dp)
                            .shimmerEffect())
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
                    .shimmerEffectAsCard(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .padding(start = 10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "           ",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier
                            .shimmerEffect()
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(end = 15.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = "                                          ",
                        modifier = Modifier
                            .height(22.dp)
                            .shimmerEffect())
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
                    .shimmerEffectAsCard()
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .padding(start = 10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "           ",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier
                            .shimmerEffect()
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(end = 15.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = "                                          ",
                        modifier = Modifier
                            .height(22.dp)
                            .shimmerEffect())
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
                    .shimmerEffectAsCard()
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .padding(start = 10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "           ",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier
                            .shimmerEffect()
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(end = 15.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = "                                          ",
                        modifier = Modifier
                            .height(22.dp)
                            .shimmerEffect())
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
                .padding(start = 15.dp, end = 15.dp, top = 25.dp)
                .clip(RoundedCornerShape(topEnd = 40.dp, bottomStart = 40.dp)),
        ) {
            Row(
                modifier = Modifier
                    .shimmerEffectAsCard()
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .padding(start = 10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "           ",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier
                            .shimmerEffect()
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(end = 15.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = "                                          ",
                        modifier = Modifier
                            .height(22.dp)
                            .shimmerEffect())
                }

            }
        }
    }
}