package com.example.phonetest.model

import com.example.phonetest.R
import com.example.phonetest.navigation.Screen

enum class ScreenTests(
    val title: Int,
    val icon: Int,
    val route: String
) {
    TOUCH_SCREEN(
        title = R.string.touch_screen,
        icon = R.drawable.touchscreen_technology,
        route = Screen.TouchScreen.route
    ),
    RED_SCREEN(
        title = R.string.red_screen,
        icon = R.drawable.red_screen,
        route = Screen.RedScreen.route
    ),
    GREEN_SCREEN(
        title = R.string.green_screen,
        icon = R.drawable.green_screen,
        route = Screen.GreenScreen.route
    ),
    BLUE_SCREEN(
        title = R.string.blue_screen,
        icon = R.drawable.blue_screen,
        route = Screen.BlueScreen.route
    ),
    BLACK_SCREEN(
        title = R.string.black_screen,
        icon = R.drawable.black_screen,
        route = Screen.BlackScreen.route
    )
}