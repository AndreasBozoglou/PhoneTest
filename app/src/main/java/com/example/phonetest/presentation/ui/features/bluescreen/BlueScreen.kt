package com.example.phonetest.presentation.ui.features.bluescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

/*
class MainActivityBlue : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_blue)
    }
}*/

@Composable
fun BlueScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue)
    )
}
