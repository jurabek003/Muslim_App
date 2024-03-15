package uz.turgunboyevjurabek.muslimapp.View.Screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import uz.turgunboyevjurabek.muslimapp.View.ButtonNavigation.ButtonNavigation

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TasbexScreen(navController: NavController) {
    Column(modifier = Modifier
        .fillMaxSize()
    ) {
        Scaffold (
            content = {

            },
            bottomBar = {
                ButtonNavigation(navController = navController)
            }
        )

    }

}