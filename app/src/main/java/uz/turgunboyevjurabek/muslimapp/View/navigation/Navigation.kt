package uz.turgunboyevjurabek.muslimapp.View.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import uz.turgunboyevjurabek.muslimapp.View.Screens.MainScreen
import uz.turgunboyevjurabek.muslimapp.View.Screens.TasbexScreen

@Composable
fun Navigation():NavController {
    val navController=rememberNavController()
    NavHost(navController = navController, startDestination = "MainScreen"){
        composable("MainScreen"){
            MainScreen(navController=navController)
        }
        composable("TasbexScreen"){
            TasbexScreen(navController = navController)
        }

    }
    return navController

}