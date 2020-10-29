package com.harrisonbacordo.flatmate.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val LightColorPalette = darkColors(
    primary = flatMateBlue,
    primaryVariant = flatMateLightBlue,
    onPrimary = grey50,
    secondary = flatMateBlue,
    onSecondary = grey50,
    error = red500,
    onError = grey50,
    surface = grey50,
    onSurface = grey900,
    background = grey50,
    onBackground = grey900
)

private val DarkColorPalette = lightColors(
    primary = flatMateBlue,
    primaryVariant = flatMateLightBlue,
    onPrimary = grey50,
    secondary = flatMateBlue,
    onSecondary = grey50,
    error = red500,
    onError = grey50,
    surface = grey800,
    onSurface = grey50,
    background = grey900,
    onBackground = grey50
)

@Composable
fun FlatMateTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}