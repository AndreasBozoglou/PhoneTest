package com.example.phonetest.model

import com.example.phonetest.R
import com.example.phonetest.navigation.Screen

enum class ScreenTests(
    val title: String,
    val icon: Int,
    val route: String
) {
    TOUCH_SCREEN(
        title = "Touch Screen",
        icon = R.drawable.touchscreen_technology,
        route = Screen.TouchScreen.route
    ),
    RED_SCREEN(
        title = "Red Screen",
        icon = R.drawable.device_mobile_fill_red,
        route = Screen.RedScreen.route
    ),
    GREEN_SCREEN(
        title = "Green Screen",
        icon = R.drawable.device_mobile_fill_green,
        route = Screen.GreenScreen.route
    ),
    BLUE_SCREEN(
        title = "Blue Screen",
        icon = R.drawable.device_mobile_fill_blue,
        route = Screen.BlueScreen.route
    ),
    BLACK_SCREEN(
        title = "Black Screen",
        icon = R.drawable.device_mobile_fill_black,
        route = Screen.BlackScreen.route
    )
}