package com.example.phonetest.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.phonetest.BlackScreen
import com.example.phonetest.MainScreen

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

        }
        composable(route = Screen.GreenScreen.route) {

        }
        composable(route = Screen.RedScreen.route) {

        }
        composable(route = Screen.MicTestScreen.route) {

        }
        composable(route = Screen.ProximityScreen.route) {

        }

    }
}