@file:OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class
)

package uz.turgunboyevjurabek.muslimapp

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size

import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import uz.turgunboyevjurabek.muslimapp.Model.navigation.BottomNavigationItem
import uz.turgunboyevjurabek.muslimapp.Model.utils.calculateQiblaDirection
import uz.turgunboyevjurabek.muslimapp.View.Screens.DayOf30Screen
import uz.turgunboyevjurabek.muslimapp.View.Screens.Dayof7Screen
import uz.turgunboyevjurabek.muslimapp.View.Screens.MainScreen
import uz.turgunboyevjurabek.muslimapp.View.Screens.QiblaScreen
import uz.turgunboyevjurabek.muslimapp.View.Screens.TasbexScreen
import uz.turgunboyevjurabek.muslimapp.View.UIutils.SheetDialogUI
import uz.turgunboyevjurabek.muslimapp.ViewModel.DataStorePreferencesViewModel.CounterViewModel
import uz.turgunboyevjurabek.muslimapp.ViewModel.Qibla.MainViewModel
import uz.turgunboyevjurabek.muslimapp.ui.theme.MuslimAppTheme
import uz.turgunboyevjurabek.qiblafinderexample.service.CompassSensorManager
import uz.turgunboyevjurabek.qiblafinderexample.service.MyLocationManager
import uz.turgunboyevjurabek.muslimapp.Model.service.PermissionsManager
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var myLocationManager: MyLocationManager

    @Inject
    lateinit var compassSensorManager: CompassSensorManager

    private lateinit var permissionsManager: PermissionsManager
    private val mainViewModel by viewModels<MainViewModel>()

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "AutoboxingStateCreation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataStore = (application as MyAplication).dataStore
        enableEdgeToEdge()
        permissionsManager = PermissionsManager(this)
        permissionsManager.onPermissionGranted = {
            myLocationManager.getLastKnownLocation()
        }
        permissionsManager.checkAndRequestLocationPermission()
        myLocationManager.onLocationReceived = { location ->
            val qiblaDirection = calculateQiblaDirection(location.latitude, location.longitude).toFloat()
            mainViewModel.updateQiblaDirection(qiblaDirection)
        }
        compassSensorManager.onDirectionChanged = { direction ->
            mainViewModel.updateCurrentDirection(direction)
        }
        setContent {
            val viewModel=CounterViewModel(dataStore)
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
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val state = mainViewModel.qiblaState.collectAsState()

                    var screenName by rememberSaveable {
                        mutableStateOf("Asosiy")
                    }

                    val topAppBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

                    var isSheetOpen  by rememberSaveable {
                        mutableStateOf(false)
                    }

                    Scaffold(
                        topBar = {
                            TopAppBar(
                                scrollBehavior = topAppBarScrollBehavior,
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
                                        navController.navigate("QiblaScreen")
                                    }) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.ic_qibla),
                                            contentDescription = "Qibla icon",
                                            Modifier.size(30.dp)
                                        )
                                    }
                                },
                            )
                        },
                        modifier = Modifier
                        .fillMaxSize(),
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
                                    TasbexScreen(
                                        navController = navController,
                                        viewModel = viewModel
                                    )
                                }
                                composable("Dayof7Screen") {
                                    Dayof7Screen(navController = navController)
                                }
                                composable("Dayof30Screen") {
                                    DayOf30Screen()
                                }
                                composable("QiblaScreen") {
                                    QiblaScreen(
                                        qiblaDirection = state.value.qiblaDirection,
                                        currentDirection = state.value.currentDirection
                                    )
                                }

                            }
                        }

                    }
                }
            }
        }
    }
}
