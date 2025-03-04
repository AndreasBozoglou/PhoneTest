package com.example.phonetest.presentation.ui.features.mictestscreen

import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.example.phonetest.R
import com.example.phonetest.presentation.theme.backgroundColor
import com.example.phonetest.presentation.theme.containerColor
import com.example.phonetest.presentation.theme.generic_components.PhoneTestCard
import com.example.phonetest.presentation.ui.features.generic_components.TopBar
import com.example.phonetest.presentation.ui.features.mictestscreen.viewmodel.MicrophoneTestViewModel
import com.example.phonetest.utils.phoneTestNavigatePopUp
import org.koin.androidx.compose.koinViewModel


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MicrophoneTestScreen(
    navController: NavController,
    viewModel: MicrophoneTestViewModel = koinViewModel()
) {
    val context = LocalContext.current
    val permissionValue = remember { mutableStateOf(false) }

    val permissionLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (!isGranted) {
                permissionValue.value = false
                Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
            } else {
                permissionValue.value = true
            }
        }

    LaunchedEffect(Unit) {
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.RECORD_AUDIO
            ) == PackageManager.PERMISSION_DENIED
        ) {
            permissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
        }
    }
    Scaffold(
        topBar = {
            TopBar(
                topBarText = "Microphone Test",
                showIcon = true,
                icon = R.drawable.caret_left,
                onIconClick = {
                    navController.phoneTestNavigatePopUp()
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FlowRow(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(containerColor)
            ) {
                PhoneTestCard(
                    text = "Start Recording",
                    icon = R.drawable.microphone
                ) {
                    if (permissionValue.value) {
                        viewModel.startRecording(context)
                    } else {
                        permissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
                    }
                }

                PhoneTestCard(
                    text = "Stop Recording",
                    icon = R.drawable.microphone_slash
                ) {
                    viewModel.stopRecording()
                }

                PhoneTestCard(
                    text = "Play Recording",
                    icon = R.drawable.play
                ) {
                    viewModel.playAudio(context)
                }

                PhoneTestCard(
                    text = "Delete Recording",
                    icon = R.drawable.trash
                ) {
                    viewModel.deleteAudio(context)
                }
            }
        }
    }
}
