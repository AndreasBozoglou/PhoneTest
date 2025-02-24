package com.example.phonetest.presentation


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.phonetest.navigation.Navigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Navigation()
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







