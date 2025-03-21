package com.example.phonetest.presentation.ui.features.mictestscreen.viewmodel

import android.content.Context
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.io.File
import java.io.IOException

class MicrophoneTestViewModel : ViewModel() {

    private var mediaRecorder: MediaRecorder? = null
    private var mediaPlayer: MediaPlayer? = null

    private val _isRecording = MutableStateFlow(false)
    val isRecording: StateFlow<Boolean> = _isRecording.asStateFlow()

    var isAudioAvailable by mutableStateOf(false)
        private set

    private fun getAudioFile(context: Context): File {
        return File(context.externalCacheDir, "audiorecordtest.3gp")
    }

    fun startRecording(context: Context) {
        if (!_isRecording.value) {
            val file = getAudioFile(context)
            mediaRecorder = MediaRecorder().apply {
                setAudioSource(MediaRecorder.AudioSource.MIC)
                setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
                setOutputFile(file.absolutePath)
                setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
                setMaxDuration(5000)

                try {
                    prepare()
                    start()
                    _isRecording.value = true
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }

            Handler(Looper.getMainLooper()).postDelayed({
                stopRecording()
            }, 5000)
        }
    }


    fun stopRecording() {
        mediaRecorder?.apply {
            stop()
            release()
        }
        mediaRecorder = null
        _isRecording.value = false
        isAudioAvailable = true
    }

    fun playAudio(context: Context) {
        val file = getAudioFile(context)
        if (!file.exists()) return
        mediaPlayer = MediaPlayer().apply {
            setDataSource(file.absolutePath)

            try {
                prepare()
                start()
                Toast.makeText(context, "Playing Audio", Toast.LENGTH_SHORT).show()
                setOnCompletionListener { stopAudio() }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private fun stopAudio() {
        mediaPlayer?.apply {
            stop()
            release()
        }
        mediaPlayer = null
    }

    fun deleteAudio(context: Context) {
        val file = getAudioFile(context)
        if (file.exists()) {
            file.delete()
            isAudioAvailable = false
            Toast.makeText(context, "File Deleted", Toast.LENGTH_SHORT).show()
        }
    }
}