package com.example.phonetest

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.biometrics.BiometricManager
import android.hardware.biometrics.BiometricPrompt
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraManager
import android.media.MediaPlayer
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Vibrator
import android.provider.MediaStore
import android.transition.TransitionInflater.from
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.biometric.BiometricPrompt.PromptInfo
import androidx.core.content.ContextCompat
import java.util.Date.from
import java.util.concurrent.Executor


class MainActivity : AppCompatActivity(), View.OnClickListener {
    var flashLightStatus: Boolean = false
    private var rq : Int = 123










    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* executor = ContextCompat.getMainExecutor(this)
        biometricPrompt = androidx.biometric.BiometricPrompt(this@MainActivity,executor, object : androidx.biometric.BiometricPrompt.AuthenticationCallback(){
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                Toast.makeText(this@MainActivity, "Fingerprint is Working", Toast.LENGTH_SHORT).show()
            }

            override fun onAuthenticationSucceeded(result: androidx.biometric.BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                Toast.makeText(this@MainActivity, "Fingerprint is Working", Toast.LENGTH_SHORT).show()
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                Toast.makeText(this@MainActivity, "Fingerprint is Working", Toast.LENGTH_SHORT).show()
            }
        })

        promptInfo = PromptInfo.Builder().setTitle("Fingerprint Test").build()*/



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
        //val btnTouchId = findViewById<Button>(R.id.btn_touch_id)
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



        var player: MediaPlayer? = null



        if (player == null){

            player = MediaPlayer.create(this, R.raw.sound)


            player.start()


            stopPlayer(player)



        }





    }

    private fun stopPlayer(player: MediaPlayer?) {
        var mdP : MediaPlayer? = player
        while (mdP != null) {
            if (player?.isPlaying ?:  Boolean == false){
                mdP.release()
                mdP = null
                Toast.makeText(this, "Media Player Released", Toast.LENGTH_SHORT).show()
            }

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

        val vibrationService = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        vibrationService.vibrate(1000)

    }

    private fun flashLight() {
        val cameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager
        val cameraId = cameraManager.cameraIdList[0]
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
    }


}



