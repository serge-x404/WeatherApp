package com.serge.weatherapp.repo

import com.serge.weatherapp.model.WeatherInfo
import javax.inject.Inject

class WeatherRepositoryImplementation @Inject constructor(): WeatherRepo {

    // Dummy data
    private val weatherData = mapOf(
        "New York" to WeatherInfo("New York",22,"Partly Cloudy"),
        "London" to WeatherInfo("London",18,"Rainy"),
        "Tokyo" to WeatherInfo("Tokyo",25,"Cloudy"),
        "Paris" to WeatherInfo("Paris",20,"Cloudy"),
        "Sydney" to WeatherInfo("Sydney",28,"Sunny"),
    )

    override fun getWeatherInfo(location: String): WeatherInfo {
        return weatherData[location] ?: WeatherInfo(location,0,"Unknown")
    }

    override fun getAllLocations(): List<String> {
        return weatherData.keys.toList()
    }

}