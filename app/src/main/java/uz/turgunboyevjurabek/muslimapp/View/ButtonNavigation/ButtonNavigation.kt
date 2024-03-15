package uz.turgunboyevjurabek.muslimapp.View.ButtonNavigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import uz.turgunboyevjurabek.muslimapp.R

@Composable
fun ButtonNavigation(navController:NavController,son:Int) {
    var tasbex by remember {
        mutableStateOf(false)
    }

    BottomAppBar(
        modifier = Modifier
            .height(60.dp)
    ) {
        Row(modifier = Modifier
            .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Box(modifier = Modifier
                .fillMaxHeight()

            ){
                Column(modifier = Modifier
                    .fillMaxHeight()
                    .clickable {
                        navController.navigate("MainScreen")
                        //tasbex=false
                    },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(imageVector = Icons.Filled.Home, contentDescription = null, Modifier.size(25.dp))
                    Text(text = "Asosiy")
                }
            }
            Box(modifier = Modifier
                .fillMaxHeight()
            ){
                Column(modifier = Modifier
                    .fillMaxHeight()
                    .clickable {
                        navController.navigate("TasbexScreen")
                        tasbex=true
                    },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = if (son==1) painterResource(id = R.drawable.tasbeh_select) else painterResource(id = R.drawable.tasbeh_unselect),
                        contentDescription = null,
                        Modifier.size(25.dp)
                    )
                    Text(text = "Tasbex")
                }
            }
            Box(modifier = Modifier
                .fillMaxHeight()
            ){
                Column(modifier = Modifier
                    .fillMaxHeight()
                    .clickable {

                    },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.i30days_unselect),
                        contentDescription = null,
                        Modifier.size(25.dp)
                    )
                    Text(text = "30 kunlik")
                }
            }
            Box(modifier = Modifier
                .fillMaxHeight()
            ){
                Column(modifier = Modifier
                    .fillMaxHeight()
                    .clickable {
                    },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                   Image(
                       painter = painterResource(id = R.drawable.i7days_unselect),
                       contentDescription = null,
                       Modifier.size(25.dp)
                   )
                    Text(text = "7 kunlik")
                }
            }
        }
    }

}

@Preview(showSystemUi = true)
@Composable
fun UI() {
    val controller= rememberNavController()
    ButtonNavigation(navController = controller)
    
}