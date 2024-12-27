package info.karambol.play.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class BillingShadow(
    val small: Dp,
    val medium: Dp,
    val large: Dp,
)

internal val appShadow = BillingShadow(
    small = 1.dp,
    medium = 2.dp,
    large = 4.dp
)
