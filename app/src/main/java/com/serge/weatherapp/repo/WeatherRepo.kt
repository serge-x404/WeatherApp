package com.serge.weatherapp.repo

import com.serge.weatherapp.model.WeatherInfo

interface WeatherRepo { // This provides weather data and it gets injected

    fun getWeatherInfo(location: String): WeatherInfo

    fun getAllLocations(): List<String>
}