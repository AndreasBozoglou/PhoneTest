package com.example.phonetest

import android.content.ContextWrapper
import android.content.pm.PackageManager
import android.media.MediaRecorder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.phonetest.R
import java.io.File
import java.util.jar.Manifest

class MainActivityMicrophone : AppCompatActivity(), View.OnClickListener {

    private var MICROPHONE_PERMISSION_CODE = 200
    private val mediaRecorder: MediaRecorder? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_microphone)

        val btnStart = findViewById<Button>(R.id.btn_start)
        val btnStop = findViewById<Button>(R.id.btn_stop)
        val btnPlay = findViewById<Button>(R.id.btn_play)
        val btnDel = findViewById<Button>(R.id.btn_delete)

        btnStart.setOnClickListener(this)
        btnStop.setOnClickListener(this)
        btnPlay.setOnClickListener(this)
        btnDel.setOnClickListener(this)

        if (isMicrophonePresent()) {
            getMicrophonePermission()
        }

    }

    override fun onClick(p0: View?) {
        when(p0?.id) {
            R.id.btn_start -> start()
            R.id.btn_stop -> stop()
            R.id.btn_play -> play()
            R.id.btn_delete -> delete()


        }
    }

    private fun play() {

        val outputFile = Environment.getExternalStorageDirectory().absolutePath + "test#gpp"

        mediaRecorder!!.setAudioSource(MediaRecorder.AudioSource.MIC)
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
        mediaRecorder.setOutputFile(outputFile)
    }



    private fun stop() {
        TODO("Not yet implemented")
    }

    private fun start() {
        TODO("Not yet implemented")
    }

    private fun delete() {
        TODO("Not yet implemented")
    }

    private fun isMicrophonePresent(): Boolean {
        if (this.packageManager.hasSystemFeature(PackageManager.FEATURE_MICROPHONE)){
            return true
        }else {
            return false
        }
    }

    private fun getMicrophonePermission () {
        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_DENIED){

            ActivityCompat.requestPermissions(this, Array<String>(1) {android.Manifest.permission.RECORD_AUDIO}, MICROPHONE_PERMISSION_CODE )
        }
    }
}