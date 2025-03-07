package com.example.phonetest.presentation.ui.features.mainscreen.viewmodel

import android.content.Context
import android.media.MediaPlayer
import androidx.lifecycle.ViewModel
import com.example.phonetest.utils.Utils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainScreenViewModel : ViewModel() {
    private var _mediaPlayer = MutableStateFlow<MediaPlayer?>(null)
    val mediaPlayer: StateFlow<MediaPlayer?> = _mediaPlayer.asStateFlow()

    private var _flashlightStatus = MutableStateFlow(false)
    val flashlightStatus: StateFlow<Boolean> = _flashlightStatus.asStateFlow()


    fun activateSpeaker(context: Context) {
        Utils.activateSpeaker(
            context,
            _mediaPlayer.value,
            _mediaPlayer.value != null
        ) { player, isPlaying ->
            _mediaPlayer.value = player
        }
    }


    fun toggleFlashlight(context: Context) {
        Utils.flashLight(context, _flashlightStatus.value) { newState ->
            _flashlightStatus.value = newState
        }
    }
}