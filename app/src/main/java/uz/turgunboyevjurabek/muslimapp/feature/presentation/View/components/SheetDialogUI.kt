@file:OptIn(ExperimentalMaterial3Api::class)

package uz.turgunboyevjurabek.muslimapp.feature.presentation.View.components

import SelectRegionViewModel
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.turgunboyevjurabek.muslimapp.feature.domein.madels.Regions

@Composable
fun SheetDialogUI(
    selectRegionViewModel: SelectRegionViewModel,
    onDismiss: () -> Unit = {}
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val colorShadow=if (isSystemInDarkTheme()) Color.Green else Color.Red
        val context= LocalContext.current
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
        LazyColumn(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(list.size){
                Surface(
                    onClick = {
                        selectRegionViewModel.saveRegion(list[it].nameRegion)
                        Toast.makeText(context, "Tanladi.", Toast.LENGTH_SHORT).show()
                        onDismiss()
                    },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .height(55.dp)
                        .fillMaxWidth(fraction = 0.8f)
                        .graphicsLayer {
                            spotShadowColor = colorShadow
                                shadowElevation = 15f
                            shape = RoundedCornerShape(bottomStart = 30.dp, topEnd = 30.dp)
                        }
                        .clip(RoundedCornerShape(bottomStart = 30.dp, topEnd = 30.dp)),
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize(),
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
                Spacer(modifier = Modifier.height(15.dp))

            }
        }
    }
    
}

@Preview(showSystemUi = true)
@Composable
private fun UISheet() {

//    SheetDialogUI()

}