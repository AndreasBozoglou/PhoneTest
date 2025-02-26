package com.example.phonetest.presentation


import android.hardware.SensorManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.phonetest.navigation.Navigation
import com.example.phonetest.presentation.theme.PhoneTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PhoneTestTheme {
                val sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
                Navigation(sensorManager)
            }

        }
    }
}