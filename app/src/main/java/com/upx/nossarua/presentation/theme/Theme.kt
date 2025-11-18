package com.upx.nossarua.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import com.google.accompanist.systemuicontroller.rememberSystemUiController

// Light Theme Colors
private val appLightColorScheme = lightColorScheme(
    primary = Jade,
    secondary = WebOrange,
    tertiary = Jade,
    background = MintCream,
    surface = MintCream,
    error = ImperialRed,
    onPrimary = LightText,
    onSecondary = DarkText,
    onBackground = DarkText,
    onSurface = DarkText,
    onError = LightText
)

// Dark Theme Colors
private val appDarkColorScheme = darkColorScheme(
    primary = DarkJade,
    secondary = WebOrange,
    tertiary = LightJade,
    background = DarkBackground,
    surface = DarkSurface,
    error = ImperialRed,
    onPrimary = LightText,
    onSecondary = DarkText,
    onBackground = LightText,
    onSurface = LightText,
    onError = DarkText
)

@Composable
fun NossaRuaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = appLightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = customTypography,
        content = content
    )
}
