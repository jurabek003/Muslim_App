package uz.turgunboyevjurabek.muslimapp.Model.navigation

import android.icu.text.CaseMap.Title
import android.widget.QuickContactBadge
import androidx.compose.ui.graphics.painter.Painter

data class BottomNavigationItem (
    val title: String,
    val selectedIcon:Painter,
    val unselectedIcon:Painter,
    val screenRout:String,
    val badgeCount:Int
)