package com.example.phonetest.navigation

import android.hardware.SensorManager
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.phonetest.presentation.ui.features.blackscreen.BlackScreen
import com.example.phonetest.presentation.ui.features.bluescreen.BlueScreen
import com.example.phonetest.presentation.ui.features.greenscreen.GreenScreen
import com.example.phonetest.presentation.ui.features.mainscreen.MainScreen
import com.example.phonetest.presentation.ui.features.proximityscreen.ProximityScreen
import com.example.phonetest.presentation.ui.features.redscreen.RedScreen
import com.example.phonetest.presentation.ui.features.touchtestscreen.TouchScreen

@Composable
fun Navigation(sensorManager: SensorManager) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.MainScreen.route
    ) {
        composable(route = Screen.MainScreen.route) {
            MainScreen(navController)
        }
        composable(route = Screen.BlackScreen.route) {
            BlackScreen()
        }
        composable(route = Screen.BlueScreen.route) {
            BlueScreen()
        }
        composable(route = Screen.GreenScreen.route) {
            GreenScreen()
        }
        composable(route = Screen.RedScreen.route) {
            RedScreen()
        }
        composable(route = Screen.MicTestScreen.route) {

        }
        composable(route = Screen.ProximityScreen.route) {
            ProximityScreen(sensorManager)
        }
        composable(route = Screen.TouchScreen.route) {
            TouchScreen()
        }

    }
}