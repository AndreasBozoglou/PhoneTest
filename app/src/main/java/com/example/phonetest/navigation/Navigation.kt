package com.example.phonetest.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.phonetest.BlackScreen
import com.example.phonetest.BlueScreen
import com.example.phonetest.GreenScreen
import com.example.phonetest.MainScreen
import com.example.phonetest.RedScreen

@Composable
fun Navigation() {
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

        }
        composable(route = Screen.TouchScreen.route) {

        }

    }
}