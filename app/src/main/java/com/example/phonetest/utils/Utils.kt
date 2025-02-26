package com.example.phonetest.utils

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraManager
import android.media.MediaPlayer
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.os.Vibrator
import android.provider.MediaStore
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.phonetest.R

object Utils {

    @RequiresApi(Build.VERSION_CODES.S)
    fun vibrate(context: Context) {
        /*val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val vibratorManager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
            vibratorManager.defaultVibrator
        } else {
            context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        }
        if (Build.VERSION.SDK_INT >= 26) {
            vibrator.vibrate(VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            vibrator.vibrate(1000)
        }*/
        val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        vibrator.vibrate(1000)
    }

    fun activateSpeaker(
        context: Context,
        player: MediaPlayer?,
        flag: Boolean,
        updateState: (MediaPlayer?, Boolean) -> Unit
    ) {
        if (player == null && !flag) {
            val newPlayer = MediaPlayer.create(context, R.raw.sound)
            newPlayer.start()
            updateState(newPlayer, true)

            Handler(Looper.getMainLooper()).postDelayed({
                newPlayer.release()
                updateState(null, false)
            }, 6000)
        }
    }

    fun openFrontCamera(context: Context) {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }

    fun flashLight(context: Context, flashLightStatus: Boolean, updateStatus: (Boolean) -> Unit) {
        val cameraManager = context.getSystemService(Context.CAMERA_SERVICE) as CameraManager
        val cameraId = cameraManager.cameraIdList[0]
        val hasFlash = context.packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)

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
            Toast.makeText(context, "Your device does not have Flashlight", Toast.LENGTH_SHORT)
                .show()
        }
    }
}