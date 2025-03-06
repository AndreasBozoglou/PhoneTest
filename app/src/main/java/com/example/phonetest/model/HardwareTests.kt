package com.example.phonetest.model

import com.example.phonetest.R
import com.example.phonetest.navigation.Screen

enum class HardwareTests(
    val title: Int,
    val icon: Int,
    val selectedIcon: Int = 0,
    val route: String = ""
) {
    VIBRATION(
        title = R.string.vibration,
        icon = R.drawable.vibrate
    ),
    SPEAKER(
        title = R.string.speaker,
        icon = R.drawable.speaker_high
    ),
    FLASHLIGHT(
        title = R.string.flash_light,
        icon = R.drawable.lightning,
        selectedIcon = R.drawable.lightning_slash
    ),
    CAMERA(
        title = R.string.camera,
        icon = R.drawable.camera
    ),
    MICROPHONE(
        title = R.string.microphone,
        icon = R.drawable.microphone,
        route = Screen.MicTestScreen.route
    ),
    PROXIMITY(
        title = R.string.proximity,
        icon = R.drawable.proximity_sensor,
        route = Screen.ProximityScreen.route
    )
}