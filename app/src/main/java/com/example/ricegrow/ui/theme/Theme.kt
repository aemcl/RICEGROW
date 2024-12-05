package com.example.ricegrow.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80,

    // Other default colors to override
    background = Color(0xFF2b2b2b),
    surface = Color(0xFF6D6D6C),
    onPrimary = Color.White,
    primaryContainer = Color(0xFF6D6D6C),//bg for container,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFFF5F5DC),
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40,

    // Other default colors to override
    background = Color(0xFFF5F5DC),
    surface = Color(0xFFECD98F),//(used for cards, sheets, etc.)
    onPrimary = Color(0xFF2b2b2b),  //(text/icons)
    primaryContainer = Color(0xFFECD98F),
    onSecondary = Color(0xFF5E5D5D),//unselected icon color
    onTertiary = Color.White,
    onBackground = Color(0xFFEEEBEB),//card names
    onSurface = Color(0xFF2b2b2b),//(text/icons on light surfaces)

)

@Composable
fun RiceGrowTheme(
    darkTheme: Boolean = isSystemInDarkTheme(), // Automatically switches based on system theme
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography, // Define your typography here
        content = content
    )
}