package com.example.phonetest.presentation.ui.features.mainscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.phonetest.R
import com.example.phonetest.model.HardwareTests
import com.example.phonetest.model.ScreenTests
import com.example.phonetest.presentation.theme.backgroundColor
import com.example.phonetest.presentation.theme.generic_components.PhoneTestCard
import com.example.phonetest.presentation.theme.generic_components.TopBar
import com.example.phonetest.presentation.ui.features.mainscreen.components.TestSection
import com.example.phonetest.presentation.ui.features.mainscreen.viewmodel.MainScreenViewModel
import com.example.phonetest.utils.Utils.openCamera
import com.example.phonetest.utils.Utils.vibrate
import com.example.phonetest.utils.phoneTestNavigateSingleTop
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(navController: NavController, viewModel: MainScreenViewModel = koinViewModel()) {
    val flashLightStatus by viewModel.flashlightStatus.collectAsState()
    val context = LocalContext.current

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
            TestSection(stringResource(R.string.screen_tests)) {
                screenTestItems.chunked(3).forEach { rowItems ->
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        rowItems.forEach { item ->
                            PhoneTestCard(
                                text = stringResource(item.title),
                                icon = item.icon,
                                modifier = Modifier.weight(1f)
                            ) {
                                navController.phoneTestNavigateSingleTop(item.route)
                            }
                        }
                        if (rowItems.size < 3) {
                            repeat(3 - rowItems.size) {
                                Spacer(modifier = Modifier.weight(1f))
                            }
                        }
                    }
                }
            }

            TestSection(stringResource(R.string.hardware_tests)) {
                hardwareTestItems.chunked(3).forEach { rowItems ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        rowItems.forEach { item ->
                            PhoneTestCard(
                                text = stringResource(item.title),
                                icon = if (stringResource(item.title) == "Flash Light") {
                                    if (flashLightStatus) item.selectedIcon else item.icon
                                } else {
                                    item.icon
                                },
                                modifier = Modifier.weight(1f)
                            ) {
                                when (item.title) {
                                    R.string.vibration -> vibrate(context)
                                    R.string.speaker -> viewModel.activateSpeaker(context)
                                    R.string.flash_light -> viewModel.toggleFlashlight(context)
                                    R.string.camera -> openCamera(context)
                                    R.string.microphone -> navController.phoneTestNavigateSingleTop(
                                        item.route
                                    )

                                    else -> navController.phoneTestNavigateSingleTop(item.route)
                                }
                            }
                        }
                        if (rowItems.size < 3) {
                            repeat(3 - rowItems.size) {
                                Spacer(modifier = Modifier.weight(1f))
                            }
                        }
                    }
                }
            }
        }
    }
}