package info.karambol.play.ui.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.graphics.Color

class BillingColor(
    primary: Color,
    secondary: Color,
    tertiary: Color,
    transparent75: Color,
    inverseColor: Color,
    textBorder: Color,
    textField: Color,
    textLabel: Color,
    staticBlack: Color,
    staticGreen: Color,
    staticWhite: Color,
    staticLightRed: Color,
    staticCyan: Color,
    unfocus: Color,

    isDarkTheme: Boolean
){
    var primary by mutableStateOf(primary, structuralEqualityPolicy())
    var secondary by mutableStateOf(secondary, structuralEqualityPolicy())
    var tertiary by mutableStateOf(tertiary, structuralEqualityPolicy())
    var transparent75 by mutableStateOf(transparent75, structuralEqualityPolicy())
    var inverseColor by mutableStateOf(inverseColor, structuralEqualityPolicy())
    var textBorder by mutableStateOf(textBorder, structuralEqualityPolicy())
    var textField by mutableStateOf(textField, structuralEqualityPolicy())
    var textLabel by mutableStateOf(textLabel, structuralEqualityPolicy())
    var staticBlack by mutableStateOf(staticBlack, structuralEqualityPolicy())
    var staticGreen by mutableStateOf(staticGreen, structuralEqualityPolicy())
    var staticWhite by mutableStateOf(staticWhite, structuralEqualityPolicy())
    var staticRed by mutableStateOf(staticLightRed, structuralEqualityPolicy())
    var staticCyan by mutableStateOf(staticCyan, structuralEqualityPolicy())
    var unfocus by mutableStateOf(unfocus, structuralEqualityPolicy())

    var isDarkTheme by mutableStateOf(isDarkTheme, structuralEqualityPolicy())

    fun copy(
        primary: Color = Color.White,
        secondary: Color = Color.White,
        tertiary: Color = Color.White,
        transparent75: Color = this.transparent75,
        inverseColor: Color = this.inverseColor,
        textBorder: Color = this.textBorder,
        textField: Color = this.textField,
        textLabel: Color = this.textLabel,
        staticBlack: Color = this.staticBlack,
        staticGreen: Color = this.staticGreen,
        staticWhite: Color = this.staticWhite,
        staticRed: Color = this.staticRed,
        staticCyan: Color = this.staticCyan,
        unfocus: Color = this.unfocus,
        isDarkTheme: Boolean = this.isDarkTheme,
    ): BillingColor = BillingColor(
        primary,
        secondary,
        tertiary,
        transparent75,
        inverseColor,
        textBorder,
        textField,
        textLabel,
        staticBlack,
        staticGreen,
        staticWhite,
        staticRed,
        staticCyan,
        unfocus,
        isDarkTheme
    )
}

internal val lightColors = BillingColor(
    primary = Color.White,
    secondary = Color(0xff2431E0),
    tertiary = Color(0xffE3E6E9),
    transparent75 = Color(0xC425273E),
    inverseColor= Color(0xFF7B7E8B),
    textBorder= Color(0xFF692C4F),
    textField= Color(0xFF6C6B6B),
    textLabel= Color(0xFF06060A),
    staticBlack= Color (0xFF000000),
    staticGreen = Color (0xFF34AA54),
    staticWhite= Color (0xFFFFFFFF),
    staticLightRed = Color (0xFFee3a57),
    staticCyan= Color(0xff00CBD7),
    unfocus = Color(0x38B8BDE8),
    isDarkTheme = false
)
internal val darkColors = BillingColor(
    primary = Color.White,
    secondary = Color(0xff2431E0),
    tertiary = Color(0xffE3E6E9),
    transparent75 = Color(0xC425273E),
    inverseColor= Color(0xFF7B7E8B),
    textBorder= Color(0xFF692C4F),
    textField= Color(0xFF6C6B6B),
    textLabel= Color(0xFF06060A),
    staticBlack= Color (0xFF000000),
    staticGreen = Color (0xFF34AA54),
    staticWhite= Color (0xFFFFFFFF),
    staticLightRed = Color (0xFFee3a57),
    staticCyan= Color(0xff00CBD7),
    unfocus = Color(0x38B8BDE8),
    isDarkTheme = true)