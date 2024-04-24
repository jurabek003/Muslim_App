@file:OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class
)

package uz.turgunboyevjurabek.muslimapp

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.provider.CalendarContract.Colors
import android.util.Log

import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Observer
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import uz.turgunboyevjurabek.muslimapp.Model.cash.DataStore
import uz.turgunboyevjurabek.muslimapp.Model.navigation.BottomNavigationItem
import uz.turgunboyevjurabek.muslimapp.Model.utils.Status
import uz.turgunboyevjurabek.muslimapp.View.Screens.Dayof30Screen
import uz.turgunboyevjurabek.muslimapp.View.Screens.Dayof7Screen
import uz.turgunboyevjurabek.muslimapp.View.Screens.MainScreen
import uz.turgunboyevjurabek.muslimapp.View.Screens.TasbexScreen
import uz.turgunboyevjurabek.muslimapp.View.UIutils.SheetDialogUI
import uz.turgunboyevjurabek.muslimapp.View.navigation.Navigation
import uz.turgunboyevjurabek.muslimapp.ViewModel.Bugungilik.BugungilkLogika
import uz.turgunboyevjurabek.muslimapp.ui.theme.MuslimAppTheme


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "AutoboxingStateCreation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MuslimAppTheme {
                val items = listOf(
                    BottomNavigationItem(
                        title = "Asosiy",
                        selectedIcon = painterResource(id = R.drawable.home_selected),
                        unselectedIcon = painterResource(id = R.drawable.home_unselected),
                        screenRout = "MainScreen",
                        badgeCount = 0
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
                    val dataStore = DataStore
                    val context = LocalContext.current
                    if (dataStore.REGION.equals("")) {
                        Toast.makeText(context, "bor ${dataStore.REGION}", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        Toast.makeText(context, "yuq", Toast.LENGTH_SHORT).show()
                    }

                    val navController = rememberNavController()
                    var screenName by rememberSaveable {
                        mutableStateOf("Asosiy")
                    }
                    val scrollBehavior =
                        TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
                    var isSheetOpen  by rememberSaveable {
                        mutableStateOf(false)
                    }
                    Scaffold(modifier = Modifier
                        .fillMaxSize()
                        .nestedScroll(scrollBehavior.nestedScrollConnection),
                        topBar = {
                            MediumTopAppBar(
                                colors = TopAppBarDefaults.topAppBarColors(
                                    containerColor = Color.Transparent,
                                    titleContentColor = MaterialTheme.colorScheme.primary,
                                ),

                                title = {
                                    Text(
                                        text = screenName.toString(),
                                        fontWeight = FontWeight.ExtraBold,
                                        fontFamily = FontFamily.Serif
                                    )
                                },
                                navigationIcon = {

                                    IconButton(
                                        onClick = {
                                            isSheetOpen = !isSheetOpen
                                    }) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.ic_location),
                                            contentDescription = "Location Region",
                                            Modifier.size(25.dp)
                                        )
                                    }
                                },
                                actions = {
                                    IconButton(onClick = {

                                    }) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.ic_qibla),
                                            contentDescription = "Qibla icon",
                                            Modifier.size(30.dp)
                                        )
                                    }
                                },
                                scrollBehavior = scrollBehavior
                            )
                        },
                        bottomBar = {
                            var selectedTabIndex by rememberSaveable {
                                mutableStateOf(0)
                            }
                            NavigationBar {
                                items.forEachIndexed { index, bottomNavigationItem ->
                                    NavigationBarItem(
                                        selected = selectedTabIndex == index,
                                        onClick = {
                                            selectedTabIndex = index
                                            navController.popBackStack()
                                            navController.navigate(bottomNavigationItem.screenRout)
                                            screenName = bottomNavigationItem.title
                                        },
                                        alwaysShowLabel = false,
                                        label = {
                                            Text(
                                                text = bottomNavigationItem.title,
                                                fontWeight = FontWeight.ExtraBold
                                            )
                                        },
                                        icon = {
                                            BadgedBox(badge = {
                                                if (bottomNavigationItem.badgeCount != 0) {
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
                    ) { innerPadding ->
                        Column(
                            modifier = Modifier
                                .padding(innerPadding)
                        ) {
                            /**
                             * For BottomSheetDialog
                             */
                            val sheetState= rememberModalBottomSheetState()
                            if (isSheetOpen){
                                ModalBottomSheet(
                                    sheetState=sheetState,
                                    tonalElevation = 20.dp,
                                    onDismissRequest = {
                                        isSheetOpen=false
                                    },

                                    windowInsets = WindowInsets(top = 50.dp)
                                    ){
                                    SheetDialogUI()
                                }
                            }
                            /**
                             * For navigation between screens
                             */
                            NavHost(
                                navController = navController,
                                startDestination = "MainScreen"
                            ) {
                                composable("MainScreen") {
                                    MainScreen(navController = navController)
                                }
                                composable("TasbexScreen") {
                                    TasbexScreen(navController = navController)
                                }
                                composable("Dayof7Screen") {
                                    Dayof7Screen(navController = navController)
                                }
                                composable("Dayof30Screen") {
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
