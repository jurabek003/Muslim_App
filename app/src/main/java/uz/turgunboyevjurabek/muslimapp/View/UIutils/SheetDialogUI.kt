@file:OptIn(ExperimentalMaterial3Api::class)

package uz.turgunboyevjurabek.muslimapp.View.UIutils

import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SheetDialogUI() {
    Column(
        modifier = Modifier
            .fillMaxSize()

    ) {
        LazyColumn() {
            items(100){

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                        .padding(vertical = 5.dp, horizontal = 30.dp)
                ) {
                    Text(
                        text = "Jurabek",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .padding(5.dp)
                            .align(Alignment.CenterHorizontally)
                    )
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