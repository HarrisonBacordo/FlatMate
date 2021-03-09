/*
 * Designed and developed by 2020 FlatMate (Harrison Bacordo)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.harrisonbacordo.flatmate.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val LightColorPalette = lightColors(
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

private val DarkColorPalette = darkColors(
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

private val AuthColorPalette = lightColors(
    primary = grey50,
    primaryVariant = flatMateLightBlue,
    onPrimary = flatMateBlue,
    secondary = flatMateBlue,
    onSecondary = grey50,
    error = red500,
    onError = grey50,
    surface = flatMateBlue,
    onSurface = grey50,
    background = flatMateBlue,
    onBackground = grey50
)

private val OnboardingColorPalette = lightColors(
    primary = grey50,
    primaryVariant = flatMateLightBlue,
    onPrimary = flatMateBlue,
    secondary = flatMateBlue,
    onSecondary = grey50,
    error = red500,
    onError = grey50,
    surface = flatMateBlue,
    onSurface = grey50,
    background = flatMateBlue,
    onBackground = grey50
)

@Composable
fun FlatMateHomeTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
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

@Composable
fun FlatmateAuthTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = AuthColorPalette,
        typography = typography,
        shapes = shapes,
        content = content
    )
}

@Composable
fun FlatmateOnboardingTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = OnboardingColorPalette,
        typography = typography,
        shapes = shapes,
        content = content
    )
}
