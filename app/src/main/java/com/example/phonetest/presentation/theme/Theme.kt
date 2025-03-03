package com.example.phonetest.presentation.theme

import android.os.Build
import android.view.View
import android.view.Window
import android.view.WindowInsetsController
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF6200EE),
    secondary = Color(0xFF03DAC5),
    background = Transparent,
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
    background = Transparent,
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
    val activity = LocalActivity.current
    val window = activity?.window

    if (window != null) {
        SetSystemBarsTransparent(window, darkTheme)
    }

    MaterialTheme(
        colorScheme = colors,
        //typography = Typography(), // You can define typography separately
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .systemBarsPadding()
            ) {
                content()
            }
        }
    )
}

fun SetSystemBarsTransparent(window: Window, darkTheme: Boolean) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val controller = window.insetsController
        controller?.setSystemBarsAppearance(
            if (darkTheme) 0 else WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
            WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
        )
    } else {
        @Suppress("DEPRECATION")
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or if (!darkTheme) View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR else 0
                )
        @Suppress("DEPRECATION")
        window.statusBarColor = android.graphics.Color.TRANSPARENT // Ensure transparency
        @Suppress("DEPRECATION")
        window.navigationBarColor = android.graphics.Color.TRANSPARENT
    }
}