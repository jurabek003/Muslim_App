@file:OptIn(ExperimentalMaterial3Api::class)

package uz.turgunboyevjurabek.muslimapp.feature.presentation.View.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import uz.turgunboyevjurabek.muslimapp.R

@Composable
fun MyTopBar(
    modifier: Modifier,
    navController: NavController,
    screenName: String,
    onSheetClick: () -> Unit,
) {
    TopAppBar(
        modifier = modifier
            .zIndex(1f),
        colors = TopAppBarDefaults.topAppBarColors(
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
                    onSheetClick()
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

}