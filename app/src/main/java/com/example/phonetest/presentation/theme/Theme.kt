package com.example.phonetest.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF6200EE),
    secondary = Color(0xFF03DAC5),
    background = Color(0xFF121212),
    surface = Color(0xFF1E1E1E),
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.White,
    onSurface = Color.White
)

private val LightColorScheme = lightColorScheme(
    primary = Purple200,
    primaryContainer = Purple700,
    onPrimary = Black,
    secondary = Teal200,
    secondaryContainer = Teal200,
    onSecondary = Black,
    background = White,
    surface = LightBlue600,
    onBackground = Black,
    onSurface = Black
)

@Composable
fun PhoneTestTheme(
    darkTheme: Boolean = true,
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colors,
        //typography = Typography(), // You can define typography separately
        content = content
    )
}