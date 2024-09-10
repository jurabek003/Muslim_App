@file:OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class
)

package uz.turgunboyevjurabek.muslimapp

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle

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
import uz.turgunboyevjurabek.muslimapp.core.MyAplication
import uz.turgunboyevjurabek.muslimapp.feature.presentation.navigation.BottomNavigationItem
import uz.turgunboyevjurabek.muslimapp.core.utils.calculateQiblaDirection
import uz.turgunboyevjurabek.muslimapp.feature.presentation.View.Screens.DayOf30Screen
import uz.turgunboyevjurabek.muslimapp.feature.presentation.View.Screens.Dayof7Screen
import uz.turgunboyevjurabek.muslimapp.feature.presentation.View.Screens.MainScreen
import uz.turgunboyevjurabek.muslimapp.feature.presentation.View.Screens.QiblaScreen
import uz.turgunboyevjurabek.muslimapp.feature.presentation.View.Screens.TasbexScreen
import uz.turgunboyevjurabek.muslimapp.feature.presentation.View.UIutils.SheetDialogUI
import uz.turgunboyevjurabek.muslimapp.feature.presentation.ViewModel.DataStorePreferencesViewModel.CounterViewModel
import uz.turgunboyevjurabek.muslimapp.feature.presentation.ViewModel.Qibla.MainViewModel
import uz.turgunboyevjurabek.muslimapp.feature.presentation.theme.MuslimAppTheme
import uz.turgunboyevjurabek.qiblafinderexample.service.CompassSensorManager
import uz.turgunboyevjurabek.qiblafinderexample.service.MyLocationManager
import uz.turgunboyevjurabek.muslimapp.feature.domein.service.PermissionsManager
import uz.turgunboyevjurabek.muslimapp.feature.presentation.View.components.MyTopBar
import uz.turgunboyevjurabek.muslimapp.feature.presentation.View.components.myBottomNavigationBar
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var myLocationManager: MyLocationManager

    @Inject
    lateinit var compassSensorManager: CompassSensorManager

    private lateinit var permissionsManager: PermissionsManager

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "AutoboxingStateCreation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataStore = (application as MyAplication).dataStore
        val mainViewModel by viewModels<MainViewModel>()
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
        compassSensorManager.registerListeners()

        setContent {
            val viewModel= CounterViewModel(dataStore)
            MuslimAppTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
                    val navController = rememberNavController()
                    val state = mainViewModel.qiblaState.collectAsState()

                    var screenName by rememberSaveable {
                        mutableStateOf("Asosiy")
                    }

                    var isSheetOpen  by rememberSaveable {
                        mutableStateOf(false)
                    }
                    Scaffold(
                        modifier = Modifier
                            .fillMaxSize(),
                        topBar = {
                            MyTopBar(
                                navController = navController,
                                screenName = screenName,
                                onSheetClick = {
                                    isSheetOpen = !isSheetOpen
                                }
                            )
                        },
                        bottomBar = {
                            screenName = myBottomNavigationBar(
                                navController = navController,
                            )
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
                                        currentDirection = state.value.currentDirection,
                                        navController = navController
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
