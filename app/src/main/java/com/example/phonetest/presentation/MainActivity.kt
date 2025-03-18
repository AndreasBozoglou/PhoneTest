package com.example.phonetest.presentation


import android.graphics.Color
import android.hardware.SensorManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.phonetest.navigation.Navigation
import com.example.phonetest.presentation.theme.PhoneTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                Color.TRANSPARENT,
                Color.TRANSPARENT
            ),
            navigationBarStyle = SystemBarStyle.light(
                Color.TRANSPARENT,
                Color.TRANSPARENT
            )
        )
        super.onCreate(savedInstanceState)
        setContent {
            PhoneTestTheme {
                val sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
                Navigation(sensorManager)
            }
        }
    }
}