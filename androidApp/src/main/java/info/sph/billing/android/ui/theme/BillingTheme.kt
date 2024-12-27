package info.karambol.play.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import com.google.accompanist.systemuicontroller.rememberSystemUiController

val LocalAppColors = staticCompositionLocalOf { lightColors }
val LocalAppTypography = staticCompositionLocalOf { appTypography }
val LocalAppSpacing = staticCompositionLocalOf { appSpacing }
val LocalAppShapes = staticCompositionLocalOf { appShapes }
val LocalAppShadow = staticCompositionLocalOf { appShadow }

@Composable
fun BillingAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val systemUiController = rememberSystemUiController()

    val colors = if (darkTheme) darkColors else lightColors
    val typography = appTypography
    val spacing = appSpacing
    val shapes = appShapes
    val shadow = appShadow

    systemUiController.setSystemBarsColor(color = colors.primary)
    systemUiController.setNavigationBarColor(color = colors.primary)

    CompositionLocalProvider(
        LocalAppColors provides colors,
        LocalAppTypography provides typography,
        LocalAppSpacing provides spacing,
        LocalAppShapes provides shapes,
        LocalAppShadow provides shadow,
        content = content
    )
}

object BillingTheme {
    val colors: BillingColor
        @Composable
        get() = LocalAppColors.current
    val typography: BillingTypography
        @Composable
        get() = LocalAppTypography.current
    val spacing: BillingSpace
        @Composable
        get() = LocalAppSpacing.current
    val shapes: BillingShapes
        @Composable
        get() = LocalAppShapes.current
    val shadow: BillingShadow
        @Composable
        get() = LocalAppShadow.current

    @Composable
    fun isDarkTheme(): Boolean {
        return isSystemInDarkTheme() // isSystemInDarkTheme()
    }
}