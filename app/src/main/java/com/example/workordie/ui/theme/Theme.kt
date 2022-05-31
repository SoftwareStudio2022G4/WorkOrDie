package com.example.workordie.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = Ocean100,     // Top bar
    secondary = Blue100,    // Add button
    surface = BlueTrans100, // Tasks boxes in All Tasks
    background = Grey900,
    onPrimary = Grey900,    // Icons on top bar
    onSecondary = Grey900,  // Plus icon on Add button
    onSurface = Blue100,    // Word in boxes
    onBackground = Blue100  // Words on background
)

private val LightColorPalette = lightColors(
    primary = Yellow100,     // Top bar
    secondary = Blue900,     // Add button
    surface = YellowTrans15, // Tasks boxes in All Tasks
    background = Yellow50,
    onPrimary = Blue900,     // Icons on top bar
    onSecondary = Yellow50,  // Plus icon on Add button
    onSurface = Blue900,     // Word in boxes
    onBackground = Blue900   // Words on background
)

@Composable
fun WorkOrDieTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}