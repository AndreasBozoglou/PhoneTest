package com.example.phonetest.presentation.ui.features.bluescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.phonetest.presentation.theme.blueScreenColor

@Composable
fun BlueScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(blueScreenColor)
    )
}
