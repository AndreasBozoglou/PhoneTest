package com.example.phonetest.presentation.ui.features.mainscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.phonetest.model.HardwareTests
import com.example.phonetest.model.ScreenTests
import com.example.phonetest.presentation.theme.backgroundColor
import com.example.phonetest.presentation.theme.containerColor
import com.example.phonetest.presentation.theme.generic_components.PhoneTestCard
import com.example.phonetest.presentation.theme.textColor
import com.example.phonetest.presentation.ui.features.generic_components.TopBar
import com.example.phonetest.presentation.ui.features.mainscreen.viewmodel.MainScreenViewModel
import com.example.phonetest.utils.phoneTestNavigateSingleTop
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun MainScreen(navController: NavController, viewModel: MainScreenViewModel = koinViewModel()) {
    val flashLightStatus by viewModel.flashlightStatus.collectAsState()

    val screenTestItems by remember { mutableStateOf(ScreenTests.entries) }
    val hardwareTestItems by remember { mutableStateOf(HardwareTests.entries) }

    Scaffold(
        topBar = {
            TopBar(
                topBarText = "Phone Test",
                showIcon = false
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center
        ) {
            FlowRow(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(containerColor)
            ) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)) {
                    Text(
                        "Screen Tests",
                        color = textColor,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                }
                screenTestItems.forEach { item ->
                    PhoneTestCard(
                        text = item.title,
                        icon = item.icon
                    ) {
                        navController.phoneTestNavigateSingleTop(item.route)
                    }
                }
            }

            FlowRow(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(containerColor)
            ) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)) {
                    Text(
                        "Hardware Tests",
                        color = textColor,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                }
                hardwareTestItems.forEach { item ->
                    PhoneTestCard(
                        text = item.title,
                        icon = if (item.title == "Flash Light") {
                            if (flashLightStatus) item.selectedIcon else item.icon

                        } else {
                            item.icon
                        }
                    ) {
                        when (item.title) {
                            "Vibration" -> {
                                viewModel.vibrate()
                            }

                            "Sound" -> {
                                viewModel.activateSpeaker()
                            }

                            "Flash Light" -> {
                                viewModel.toggleFlashlight()
                            }

                            "Camera" -> {
                                viewModel.openCamera()
                            }

                            "Microphone" -> {
                                navController.phoneTestNavigateSingleTop(item.route)
                            }

                            else -> {
                                navController.phoneTestNavigateSingleTop(item.route)
                            }
                        }
                    }
                }
            }

            /*Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                PhoneTestCard(text = "TOUCH") {
                    navController.phoneTestNavigateSingleTop(Screen.TouchScreen.route)
                }
                PhoneTestCard(text = "VIBRATION") {
                    vibrate(context)
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
            }*/
        }
    }
}


