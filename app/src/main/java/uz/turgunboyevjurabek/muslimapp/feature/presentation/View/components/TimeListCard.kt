package uz.turgunboyevjurabek.muslimapp.feature.presentation.View.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.turgunboyevjurabek.muslimapp.feature.domein.madels.bugungilik.Bugungi

@Composable
fun TimeListCard(modifier: Modifier = Modifier,todayData:Bugungi) {

    Column(
        modifier = Modifier
            .clipToBounds()  // Itemlar kartaning ostida yashirilishi uchun
            .verticalScroll(rememberScrollState())
    ) {
        /**
         * Bomdod
         */
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(start = 20.dp, end = 20.dp, top = 25.dp)
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
                .padding(start = 20.dp, end = 20.dp, top = 25.dp)
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
                .padding(start = 20.dp, end = 20.dp, top = 25.dp)
                .clip(RoundedCornerShape(topEnd = 40.dp, bottomStart = 40.dp)),
        ) {
            Row(
                modifier = Modifier
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
                .padding(start = 20.dp, end = 20.dp, top = 25.dp)
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
                .padding(start = 20.dp, end = 20.dp, top = 25.dp, bottom = 5.dp)
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