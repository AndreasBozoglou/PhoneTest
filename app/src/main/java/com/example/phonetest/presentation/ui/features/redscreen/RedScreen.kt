package com.example.phonetest.presentation.ui.features.redscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.phonetest.presentation.theme.redScreenColor

@Composable
fun RedScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(redScreenColor)
    )
}