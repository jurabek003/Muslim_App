@file:OptIn(ExperimentalMaterial3Api::class)

package uz.turgunboyevjurabek.muslimapp.View.UIutils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.turgunboyevjurabek.muslimapp.Model.madels.Regions

@Composable
fun SheetDialogUI() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val list=ArrayList<Regions>()
        list.addAll(listOf(
            Regions("Farg'ona"),
            Regions("Qo'qon"),
            Regions("Andijon"),
            Regions("Namangan"),
            Regions("Qarshi"),
            Regions("Namangan"),
            Regions("Toshkent"),
            Regions("Samarqand"),
            Regions("Urganch"),
            Regions("Marg'ilon"),
            Regions("Jizzax"),
            Regions("Denov"),
            Regions("Angren"),
            Regions("O'sh"),
            Regions("Buxoro"),
            Regions("Jambul"),
            Regions("Turkiston"),
            Regions("Konibodom"),
            Regions("Xo'jand"),
            Regions("Bishkek"),
            Regions("Bekobod"),
            Regions("Guliston"),
            Regions("Navoiy"),
            Regions("Xiva"),
            Regions("Nukus"),
        ))
        LazyColumn() {
            items(list.size){
                Card(
                    modifier = Modifier
                        .height(70.dp)
                        .align(Alignment.CenterHorizontally)
                        .padding(vertical = 6.dp, horizontal = 30.dp)
                        .clip(RoundedCornerShape(bottomStart = 30.dp, topEnd = 30.dp))
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.horizontalGradient(
                                    listOf(
                                        MaterialTheme.colorScheme.primary,
                                        MaterialTheme.colorScheme.onPrimary
                                    ),
                                )),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = list[it].nameRegion,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 20.sp,
                            fontFamily = FontFamily.Serif,
                        )
                    }
                }

            }
        }
    }
    
}

@Preview(showSystemUi = true)
@Composable
private fun UISheet() {

    SheetDialogUI()

}