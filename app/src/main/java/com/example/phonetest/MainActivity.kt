package com.example.phonetest

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CameraManager
import android.media.MediaPlayer
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
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
import androidx.compose.ui.unit.dp
import androidx.core.app.ComponentActivity
import androidx.core.os.postDelayed

class MainActivity : androidx.activity.ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }

    @Composable
    fun MainScreen() {
        var flashLightStatus by remember { mutableStateOf(false) }
        var flag by remember { mutableStateOf(false) }
        var player by remember { mutableStateOf<MediaPlayer?>(null) }

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
                    startActivity(
                        Intent(
                            this@MainActivity,
                            TouchScreen::class.java
                        )
                    )
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
                    startActivity(
                        Intent(
                            this@MainActivity,
                            MainActivityMicrophone::class.java
                        )
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                CustomButton(text = "RED") {
                    startActivity(
                        Intent(
                            this@MainActivity,
                            MainActivityRed::class.java
                        )
                    )
                }
                CustomButton(text = "GREEN") {
                    startActivity(
                        Intent(
                            this@MainActivity,
                            MainActivityGreen::class.java
                        )
                    )
                }
                CustomButton(text = "BLUE") {
                    startActivity(
                        Intent(
                            this@MainActivity,
                            MainActivityBlue::class.java
                        )
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                CustomButton(text = "BLACK") {
                    startActivity(
                        Intent(
                            this@MainActivity,
                            MainActivityBlack::class.java
                        )
                    )
                }
                CustomButton(text = "PROXIMITY") {
                    startActivity(
                        Intent(
                            this@MainActivity,
                            MainActivityProximity::class.java
                        )
                    )
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
        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= 26) {
            vibrator.vibrate(VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            vibrator.vibrate(1000)
        }
    }

    private fun activateSpeaker(player: MediaPlayer?, flag: Boolean, updateState: (MediaPlayer?, Boolean) -> Unit) {
        if (player == null && !flag) {
            val newPlayer = MediaPlayer.create(this, R.raw.sound)
            newPlayer.start()
            updateState(newPlayer, true)

            Handler(Looper.getMainLooper()).postDelayed({
                newPlayer.release()
                updateState(null, false)
            }, 6000)
        }
    }

    private fun openFrontCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, 123)
    }

    private fun flashLight(flashLightStatus: Boolean, updateStatus: (Boolean) -> Unit) {
        val cameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager
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
        }
    }

}

/*class MainActivity : AppCompatActivity(), View.OnClickListener {
    var flashLightStatus: Boolean = false
    private var rq : Int = 123
    var flag = false
    var player: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        val btnVibrator = findViewById<Button>(R.id.btn_vibrator)
        val btnFlashLight = findViewById<Button>(R.id.btn_flash_light)
        val btnFrontCamera = findViewById<Button>(R.id.btn_camera)
        val btnRed = findViewById<Button>(R.id.btn_red)
        val btnGreen = findViewById<Button>(R.id.btn_green)
        val btnBlue = findViewById<Button>(R.id.btn_blue)
        val btnBlack = findViewById<Button>(R.id.btn_black)
        val btnSound = findViewById<Button>(R.id.btn_sound)
        val btnProximity = findViewById<Button>(R.id.btn_proximity)
        val btnTouch = findViewById<Button>(R.id.btn_touch)
        val btnMic = findViewById<Button>(R.id.btn_mic_test)








        btnVibrator.setOnClickListener(this)
        btnFlashLight.setOnClickListener(this)
        btnFrontCamera.setOnClickListener(this)
        btnRed.setOnClickListener(this)
        btnBlue.setOnClickListener(this)
        btnGreen.setOnClickListener(this)
        btnBlack.setOnClickListener(this)
        btnSound.setOnClickListener(this)
        btnProximity.setOnClickListener(this)
        btnTouch.setOnClickListener(this)
        btnMic.setOnClickListener(this)








    }



    override fun onClick(p0: View?) {



        when (p0?.id) {
            R.id.btn_vibrator -> vibrate()
            R.id.btn_flash_light -> flashLight()
            R.id.btn_camera -> openFrontCamera()
            R.id.btn_red -> redScreen()
            R.id.btn_blue -> blueScreen()
            R.id.btn_green -> greenScreen()
            R.id.btn_black -> blackScreen()
            R.id.btn_sound -> activateSpeaker()
            R.id.btn_proximity -> proximity()
            R.id.btn_touch -> touch()
            R.id.btn_mic_test -> microphone()






        }
    }

    private fun microphone() {
        val intent = Intent(this, MainActivityMicrophone::class.java)
        startActivity(intent)
    }


    private fun touch() {
        val intent = Intent(this, TouchScreen::class.java)
        startActivity(intent)
    }

    private fun proximity() {

        val intent = Intent(this, MainActivityProximity::class.java)
        startActivity(intent)


    }

    private fun blackScreen() {
        val intent = Intent(this, MainActivityBlack::class.java)
        startActivity(intent)
    }

    private fun activateSpeaker() {


        if (player == null && !flag){

            flag = true
            player = MediaPlayer.create(this, R.raw.sound)


            player?.start()
            //stopPlayer(player)

            Handler(Looper.getMainLooper()).postDelayed({
                flag = false
                player?.release()
                player = null

            },6000)

        }





    }


    private fun greenScreen() {
        val intent = Intent(this, MainActivityGreen::class.java)
        startActivity(intent)
    }

    private fun blueScreen() {
        val intent = Intent(this, MainActivityBlue::class.java)
        startActivity(intent)
    }

    private fun redScreen() {
        val intent = Intent(this, MainActivityRed::class.java)
        startActivity(intent)

    }

    private fun openFrontCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        startActivityForResult(intent,rq)

    }




    private fun vibrate() {

        val vibrator = this.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT>=26){
            vibrator.vibrate(VibrationEffect.createOneShot(1000,VibrationEffect.DEFAULT_AMPLITUDE))
        }else
            vibrator.vibrate(1000)



    }

    private fun flashLight() {
        val cameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager
        val cameraId = cameraManager.cameraIdList[0]
        val hasFlash = this.packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)
        if(hasFlash){
            if (!flashLightStatus) {
                try {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        cameraManager.setTorchMode(cameraId, true)
                    }
                    flashLightStatus = true

                } catch (e: CameraAccessException) {
                }
            } else {
                try {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        cameraManager.setTorchMode(cameraId, false)
                    }
                    flashLightStatus = false
                } catch (e: CameraAccessException) {
                }
            }
        }else{
            Toast.makeText(this,"Your device does not have Flashlight",Toast.LENGTH_SHORT).show()
        }

    }




}*/







