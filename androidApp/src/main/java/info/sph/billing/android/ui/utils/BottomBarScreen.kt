package info.sph.billing.android.ui.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Setting : BottomBarScreen(
        route = "SETTING",
        title = "SETTING",
        icon = Icons.Default.Settings
    )

    object DebitCards : BottomBarScreen(
        route = "DEBIT_CARDS",
        title = "DEBIT_CARDS",
        icon = Icons.Default.AccountBox
    )

    object Statement : BottomBarScreen(
        route = "STATEMENT",
        title = "STATEMENT",
        icon = Icons.Default.List
    )

    object Home : BottomBarScreen(
        route = "HOME",
        title = "HOME",
        icon = Icons.Default.Home
    )
}