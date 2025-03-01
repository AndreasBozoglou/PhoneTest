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
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.provider.MediaStore
import android.widget.Toast
import com.example.phonetest.R

object Utils {


    fun vibrate(context: Context) {
        val vibrator: Vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val vibratorManager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
            vibratorManager.defaultVibrator
        } else {
            context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        }

        val DELAY = 0L
        val VIBRATE = 1000L
        val SLEEP = 1000L


        val vibrationPattern = longArrayOf(DELAY, VIBRATE, SLEEP)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(
                VibrationEffect.createWaveform(
                    vibrationPattern,
                    VibrationEffect.DEFAULT_AMPLITUDE
                )
            )
        } else {
            vibrator.vibrate(vibrationPattern, VibrationEffect.DEFAULT_AMPLITUDE)
        }
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