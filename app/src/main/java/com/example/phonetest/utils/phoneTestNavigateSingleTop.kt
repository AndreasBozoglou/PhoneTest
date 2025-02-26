package com.example.phonetest.utils

import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder

fun NavController.phoneTestNavigateSingleTop(
    route: String,
    navBuilder: NavOptionsBuilder.() -> Unit = {}
) {
    navigate(route) {
        this.apply(navBuilder)
        launchSingleTop = true
    }
}