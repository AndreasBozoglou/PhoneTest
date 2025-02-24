package com.example.phonetest.presentation.ui.features.mainscreen

import android.media.MediaPlayer
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.phonetest.navigation.Screen

@Composable
fun MainScreen(navController: NavController) {
    var flashLightStatus by remember { mutableStateOf(false) }
    var flag by remember { mutableStateOf(false) }
    var player by remember { mutableStateOf<MediaPlayer?>(null) }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            CustomButton(text = "TOUCH") {
                /*startActivity(
                    Intent(
                        this@MainActivity,
                        TouchScreen::class.java
                    )
                )*/
            }
            CustomButton(text = "VIBRATION") { vibrate() }
            CustomButton(text = "SOUND") {
                activateSpeaker(player, flag) { updatedPlayer, updatedFlag ->
                    player = updatedPlayer
                    flag = updatedFlag
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            CustomButton(text = "FLASH LIGHT") {
                flashLight(flashLightStatus) {
                    flashLightStatus = it
                }
            }
            CustomButton(text = "CAMERA") { openFrontCamera() }
            CustomButton(text = "MIC TEST") {
                /*startActivity(
                    Intent(
                        this@MainActivity,
                        MainActivityMicrophone::class.java
                    )
                )*/
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            CustomButton(text = "RED") {
                navController.navigate(Screen.RedScreen.route)
            }
            CustomButton(text = "GREEN") {
                navController.navigate(Screen.GreenScreen.route)
            }
            CustomButton(text = "BLUE") {
                navController.navigate(Screen.BlueScreen.route)
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            CustomButton(text = "BLACK") {
                navController.navigate(Screen.BlackScreen.route)
            }
            CustomButton(text = "PROXIMITY") {
                navController.navigate(Screen.ProximityScreen.route)
            }
        }
    }
}

@Composable
fun CustomButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.padding(4.dp)
    ) {
        Text(text)
    }
}

private fun vibrate() {
    /*val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    if (Build.VERSION.SDK_INT >= 26) {
        vibrator.vibrate(VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE))
    } else {
        vibrator.vibrate(1000)
    }*/
}

private fun activateSpeaker(player: MediaPlayer?, flag: Boolean, updateState: (MediaPlayer?, Boolean) -> Unit) {
    /*if (player == null && !flag) {
        val newPlayer = MediaPlayer.create(LocalContext.current, R.raw.sound)
        newPlayer.start()
        updateState(newPlayer, true)

        Handler(Looper.getMainLooper()).postDelayed({
            newPlayer.release()
            updateState(null, false)
        }, 6000)
    }*/
}

private fun openFrontCamera() {
    /*val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
    startActivityForResult(intent, 123)*/
}

private fun flashLight(flashLightStatus: Boolean, updateStatus: (Boolean) -> Unit) {
    /*val cameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager
    val cameraId = cameraManager.cameraIdList[0]
    val hasFlash = packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)

    if (hasFlash) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                cameraManager.setTorchMode(cameraId, !flashLightStatus)
            }
            updateStatus(!flashLightStatus)
        } catch (e: CameraAccessException) {
            e.printStackTrace()
        }
    } else {
        Toast.makeText(this, "Your device does not have Flashlight", Toast.LENGTH_SHORT).show()
    }*/
}