package com.example.phonetest.presentation.ui.features.mictestscreen

import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.LocalActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import com.example.phonetest.R
import com.example.phonetest.presentation.theme.backgroundColor
import com.example.phonetest.presentation.theme.containerItemColor
import com.example.phonetest.presentation.theme.generic_components.PhoneTestAlertDialog
import com.example.phonetest.presentation.theme.generic_components.PhoneTestButton
import com.example.phonetest.presentation.theme.generic_components.TopBar
import com.example.phonetest.presentation.theme.textColor
import com.example.phonetest.presentation.ui.features.mictestscreen.components.WaveformAnimation
import com.example.phonetest.presentation.ui.features.mictestscreen.viewmodel.MicrophoneTestViewModel
import com.example.phonetest.utils.goToAppSettings
import com.example.phonetest.utils.phoneTestNavigatePopUp
import org.koin.androidx.compose.koinViewModel


@Composable
fun MicrophoneTestScreen(
    navController: NavController,
    viewModel: MicrophoneTestViewModel = koinViewModel()
) {
    val context = LocalContext.current
    val activity = LocalActivity.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val isRecording by viewModel.isRecording.collectAsState()
    val permissionValue = remember { mutableStateOf(false) }
    val showPermissionAlertDialog = remember { mutableStateOf(false) }


    val permissionLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            permissionValue.value = isGranted
            if (!isGranted) {
                showPermissionAlertDialog.value = true
                Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }

    LaunchedEffect(lifecycleOwner) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
            permissionValue.value = ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.RECORD_AUDIO
            ) == PackageManager.PERMISSION_GRANTED
        }
    }

    Scaffold(
        topBar = {
            TopBar(
                topBarText = stringResource(R.string.microphone_test),
                showIcon = true,
                icon = R.drawable.caret_left,
                onIconClick = {
                    navController.phoneTestNavigatePopUp()
                }
            )
        }
    ) { paddingValues ->
        if (showPermissionAlertDialog.value) {
            PhoneTestAlertDialog(
                title = stringResource(R.string.mic_permission_alert_title),
                body = stringResource(R.string.mic_permission_alert_body),
                buttonText = stringResource(R.string.mic_permission_alert_button),
                onDismiss = { showPermissionAlertDialog.value = false },
                onOkayClick = {
                    activity?.goToAppSettings()
                    showPermissionAlertDialog.value = false
                }
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .fillMaxWidth()
                    .background(containerItemColor)
                    .height(250.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(
                        if (isRecording) R.drawable.microphone_slash else R.drawable.microphone
                    ),
                    contentDescription = "Microphone",
                    modifier = Modifier
                        .clickable {
                            if (permissionValue.value) {
                                if (isRecording) {
                                    viewModel.stopRecording()
                                } else {
                                    viewModel.startRecording(context)
                                }
                            } else {
                                permissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
                            }
                        }
                        .size(64.dp),
                    tint = Color.Red
                )
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = if (isRecording) stringResource(R.string.tap_to_stop_recording) else stringResource(
                        R.string.tap_to_start_recording
                    ),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = textColor
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    if (isRecording) {
                        WaveformAnimation()
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                PhoneTestButton(
                    icon = R.drawable.play,
                    text = stringResource(R.string.play),
                    onClick = { viewModel.playAudio(context) })
                PhoneTestButton(
                    icon = R.drawable.trash,
                    text = stringResource(R.string.delete),
                    onClick = { viewModel.deleteAudio(context) })
            }
        }
    }
}


