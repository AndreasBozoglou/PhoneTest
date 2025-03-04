package com.example.phonetest.model

import com.example.phonetest.R
import com.example.phonetest.navigation.Screen

enum class HardwareTests(
    val title: String,
    val icon: Int,
    val selectedIcon: Int = 0,
    val route: String = ""
) {
    VIBRATION(
        title = "Vibration",
        icon = R.drawable.vibrate
    ),
    SOUND(
        title = "Sound",
        icon = R.drawable.speaker_high
    ),
    FLASHLIGHT(
        title = "Flash Light",
        icon = R.drawable.lightning,
        selectedIcon = R.drawable.lightning_slash
    ),
    CAMERA(
        title = "Camera",
        icon = R.drawable.camera
    ),
    MICROPHONE(
        title = "Microphone",
        icon = R.drawable.microphone,
        route = Screen.MicTestScreen.route
    ),
    PROXIMITY(
        title = "Proximity",
        icon = R.drawable.proximity_sensor,
        route = Screen.ProximityScreen.route
    )
}