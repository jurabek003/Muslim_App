package uz.turgunboyevjurabek.muslimapp.feature.presentation.View.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import uz.turgunboyevjurabek.muslimapp.R
import uz.turgunboyevjurabek.muslimapp.feature.presentation.navigation.BottomNavigationItem

@Composable
fun myBottomNavigationBar(
    navController: NavController,
    modifier: Modifier = Modifier
) :String{
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
    var selectedTabIndex by rememberSaveable {
        mutableIntStateOf(0)
    }
    var screenName by rememberSaveable {
        mutableStateOf("Asosiy")
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
    return screenName
}