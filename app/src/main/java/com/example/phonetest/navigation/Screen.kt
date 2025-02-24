package com.example.phonetest.navigation

sealed class Screen(
    val route: String
) {
    data object BlackScreen: Screen(route = "black_screen")
    data object RedScreen: Screen(route = "red_screen")
    data object GreenScreen: Screen(route = "green_screen")
    data object BlueScreen: Screen(route = "blue_screen")
    data object ProximityScreen: Screen(route = "proximity_screen")
    data object TouchScreen: Screen(route = "touch_screen")
    data object MicTestScreen: Screen(route = "mic_test_screen")
    data object MainScreen: Screen(route = "main_screen")
}
