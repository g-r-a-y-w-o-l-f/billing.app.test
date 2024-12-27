package info.karambol.play.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class BillingSpace(
    val space02: Dp,
    val space04: Dp,
    val space08: Dp,
    val space12: Dp,
    val space16: Dp,
    val space18: Dp,
    val space24: Dp,
    val space32: Dp,
    val space38: Dp,
    val space48: Dp,
    val space56: Dp
)

internal val appSpacing = BillingSpace(
    space02 =  2.dp,
    space04 =  4.dp,
    space08 =  8.dp,
    space12 = 12.dp,
    space16 = 16.dp,
    space18 = 18.dp,
    space24 = 24.dp,
    space32 = 32.dp,
    space38 = 38.dp,
    space48 = 48.dp,
    space56 = 56.dp
)
