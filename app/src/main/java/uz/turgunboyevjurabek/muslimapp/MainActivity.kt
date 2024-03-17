@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class
)

package uz.turgunboyevjurabek.muslimapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import uz.turgunboyevjurabek.muslimapp.Model.navigation.BottomNavigationItem
import uz.turgunboyevjurabek.muslimapp.View.Screens.Dayof30Screen
import uz.turgunboyevjurabek.muslimapp.View.Screens.Dayof7Screen
import uz.turgunboyevjurabek.muslimapp.View.Screens.MainScreen
import uz.turgunboyevjurabek.muslimapp.View.Screens.TasbexScreen
import uz.turgunboyevjurabek.muslimapp.View.navigation.Navigation
import uz.turgunboyevjurabek.muslimapp.ui.theme.MuslimAppTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "AutoboxingStateCreation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MuslimAppTheme {
                val items = listOf(
                    BottomNavigationItem(
                        title = "Asosiy",
                        selectedIcon = painterResource(id = R.drawable.i30days_select),
                        unselectedIcon = painterResource(id = R.drawable.i30days_unselect),
                        screenRout = "MainScreen",
                        badgeCount = 7
                    ),
                    BottomNavigationItem(
                        title = "Tasbex",
                        selectedIcon = painterResource(id = R.drawable.tasbeh_select),
                        unselectedIcon = painterResource(id = R.drawable.tasbeh_unselect),
                        screenRout = "TasbexScreen",
                        badgeCount = 0
                    ),
                    BottomNavigationItem(
                        title = "7 Kunlik",
                        selectedIcon = painterResource(id = R.drawable.i7days_select),
                        unselectedIcon = painterResource(id = R.drawable.i7days_unselect),
                        screenRout = "Dayof7Screen",
                        badgeCount = 0
                    ),
                    BottomNavigationItem(
                        title = "30 Kunlik",
                        selectedIcon = painterResource(id = R.drawable.i30days_select),
                        unselectedIcon = painterResource(id = R.drawable.i30days_unselect),
                        screenRout = "Dayof30Screen",
                        badgeCount = 0
                    ),
                )
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {

                    val navController= rememberNavController()
                    var screenName by rememberSaveable {
                        mutableStateOf("")
                    }
                    Scaffold(modifier = Modifier.fillMaxSize(),
                        topBar = {
                                 TopAppBar(title = { Text(
                                     text = screenName.toString(),
                                     color = MaterialTheme.colorScheme.primary
                                 )})
                        },
                        bottomBar = {
                        var selectedTabIndex by rememberSaveable {
                            mutableStateOf(0)
                        }
                        NavigationBar {
                            items.forEachIndexed { index, bottomNavigationItem ->
                                NavigationBarItem(selected = selectedTabIndex == index,
                                    onClick = {
                                        selectedTabIndex = index
                                        navController.navigate(bottomNavigationItem.screenRout)
                                        screenName=bottomNavigationItem.title
                                              },
                                    label = { Text(text = bottomNavigationItem.title) },
                                    icon = {
                                        BadgedBox(badge = {
                                            if (bottomNavigationItem.badgeCount!=0){
                                                Badge(content = {
                                                    Text(text = bottomNavigationItem.badgeCount.toString())
                                                })
                                            }
                                        }) {
                                            Icon(
                                                painter = if (selectedTabIndex == index) {
                                                    bottomNavigationItem.selectedIcon
                                                } else {
                                                    bottomNavigationItem.unselectedIcon
                                                },
                                                contentDescription = bottomNavigationItem.title,
                                                modifier = Modifier.size(25.dp)
                                            )
                                        }
                                    }

                                )

                            }
                        }
                    }
                    ){innerPadding->
                        Column(modifier = Modifier
                            .padding(innerPadding)) {
                            NavHost(navController = navController, startDestination = "MainScreen"){
                                composable("MainScreen"){
                                    MainScreen(navController=navController)
                                }
                                composable("TasbexScreen"){
                                    TasbexScreen(navController = navController)
                                }
                                composable("Dayof7Screen"){
                                    Dayof7Screen()
                                }
                                composable("Dayof30Screen"){
                                    Dayof30Screen()
                                }

                            }
                        }

                    }
                }
            }
        }
    }
}
