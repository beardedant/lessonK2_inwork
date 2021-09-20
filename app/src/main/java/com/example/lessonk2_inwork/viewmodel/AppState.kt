package com.example.lessonk2_inwork.viewmodel

import com.example.lessonk2_inwork.domen.Weather

sealed class AppState {
    object Loading :AppState()
    data class Success(val weatherData: List<Weather>) : AppState()
    data class Error(val error: Throwable) : AppState()

}
