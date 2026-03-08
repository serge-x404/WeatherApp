package com.serge.weatherapp.viewmodel

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.serge.weatherapp.repo.WeatherRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepo: WeatherRepo
): ViewModel() {

    // Ui State
    private val _uiState = MutableStateFlow<WeatherUiState>(WeatherUiState.Loading)
    val uiState: StateFlow<WeatherUiState> = _uiState.asStateFlow()

    private val _locations = MutableStateFlow<List<String>>(emptyList())
    val locations: StateFlow<List<String>> = _locations.asStateFlow()

    private val _selectedLocation = MutableStateFlow<String>("")
    val selectedLocation: StateFlow<String> = _selectedLocation.asStateFlow()


    init {
        loadLocations()
    }

    private fun loadLocations() {
        viewModelScope.launch {
            val locationList = weatherRepo.getAllLocations()
            _locations.value = locationList

            if (locationList.isNotEmpty()) {
                _selectedLocation.value = locationList[0]
                loadWeatherForLocation(locationList[0])
            }

        }
    }

    private fun loadWeatherForLocation(location: String) {
        viewModelScope.launch {
            _uiState.value = WeatherUiState.Loading

            val weatherInfo = weatherRepo.getWeatherInfo(location)
            _uiState.value = WeatherUiState.Success(weatherInfo)
        }
    }

    fun selectedLocation(location: String) {
        _selectedLocation.value = location
        loadWeatherForLocation(location)
    }
}