package com.example.phonetest.presentation.ui.features.mainscreen

import android.media.MediaPlayer
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.phonetest.navigation.Screen
import com.example.phonetest.presentation.theme.generic_components.PhoneTestCard
import com.example.phonetest.utils.Utils.activateSpeaker
import com.example.phonetest.utils.Utils.flashLight
import com.example.phonetest.utils.Utils.openFrontCamera
import com.example.phonetest.utils.Utils.vibrate
import com.example.phonetest.utils.phoneTestNavigateSingleTop

@Composable
fun MainScreen(navController: NavController) {
    var flashLightStatus by remember { mutableStateOf(false) }
    var flag by remember { mutableStateOf(false) }
    var player by remember { mutableStateOf<MediaPlayer?>(null) }
    val context = LocalContext.current
    val hapticFeedback = LocalHapticFeedback.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            PhoneTestCard(text = "TOUCH") {
                navController.phoneTestNavigateSingleTop(Screen.TouchScreen.route)
            }
            PhoneTestCard(text = "VIBRATION") {
                vibrate(context)
                //hapticFeedback.performHapticFeedback(HapticFeedbackType.TextHandleMove)
            }
            PhoneTestCard(text = "SOUND") {
                activateSpeaker(context, player, flag) { updatedPlayer, updatedFlag ->
                    player = updatedPlayer
                    flag = updatedFlag
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            PhoneTestCard(text = "FLASH LIGHT") {
                flashLight(context, flashLightStatus) {
                    flashLightStatus = it
                }
            }
            PhoneTestCard(text = "CAMERA") { openFrontCamera(context) }
            PhoneTestCard(text = "MIC TEST") {
                navController.phoneTestNavigateSingleTop(Screen.MicTestScreen.route)
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            PhoneTestCard(text = "RED") {
                navController.phoneTestNavigateSingleTop(Screen.RedScreen.route)
            }
            PhoneTestCard(text = "GREEN") {
                navController.phoneTestNavigateSingleTop(Screen.GreenScreen.route)
            }
            PhoneTestCard(text = "BLUE") {
                navController.phoneTestNavigateSingleTop(Screen.BlueScreen.route)
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            PhoneTestCard(text = "BLACK") {
                navController.phoneTestNavigateSingleTop(Screen.BlackScreen.route)
            }
            PhoneTestCard(text = "PROXIMITY") {
                navController.phoneTestNavigateSingleTop(Screen.ProximityScreen.route)
            }
        }
    }
}


