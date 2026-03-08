package com.serge.weatherapp.viewmodel

import com.serge.weatherapp.model.WeatherInfo

sealed class WeatherUiState {   // a sealed class restricts which classes can inherit from it

    data object Loading: WeatherUiState()
    data class Success(val weatherInfo: WeatherInfo): WeatherUiState()
    data class Failure(val errorMessage: String): WeatherUiState()
}