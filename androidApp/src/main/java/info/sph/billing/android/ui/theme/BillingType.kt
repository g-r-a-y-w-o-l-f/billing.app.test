package info.karambol.play.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import info.sph.billing.android.R


val poppinsFontFamily = FontFamily(
    Font(R.font.poppins_regular, FontWeight.W400),
    Font(R.font.poppins_semi_bold, FontWeight.W600),
)

@Immutable
data class BillingTypography(
    val titleXxl: TextStyle,
    val titleXs: TextStyle,
    val body: TextStyle,
    val small: TextStyle
)

internal val appTypography = BillingTypography(
    titleXxl = TextStyle(
        fontSize = 21.sp,
        fontWeight = FontWeight.W400,
        fontFamily = poppinsFontFamily
    ),
    titleXs = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.W600,
        fontFamily = poppinsFontFamily
    ),
    body = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.W400,
        fontFamily = poppinsFontFamily
    ),
    small = TextStyle(
        fontSize = 10.sp,
        fontWeight = FontWeight.W400,
        fontFamily = poppinsFontFamily
    )
)
