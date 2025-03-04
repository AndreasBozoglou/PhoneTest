package com.example.phonetest.di

import com.example.phonetest.presentation.ui.features.mainscreen.viewmodel.MainScreenViewModel
import com.example.phonetest.presentation.ui.features.mictestscreen.viewmodel.MicrophoneTestViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::MainScreenViewModel)
    viewModelOf(::MicrophoneTestViewModel)
}