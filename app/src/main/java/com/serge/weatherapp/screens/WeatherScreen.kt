package com.serge.weatherapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.LoadingIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.serge.weatherapp.model.WeatherInfo
import com.serge.weatherapp.viewmodel.WeatherUiState
import com.serge.weatherapp.viewmodel.WeatherViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel = hiltViewModel()
) {


    // Collecting stateflow as compose states
    val uiState by viewModel.uiState.collectAsState()
    val locations by viewModel.locations.collectAsState()
    val selectedLocation by viewModel.selectedLocation.collectAsState()


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Weather App with Hilt")
                }
            )
        }
    ) {it -> Modifier.padding(it)
        Column(
            modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LocationSelector(
                locations = locations,
                selectedLocation = selectedLocation,
                onLocationSelected = { location ->
                    viewModel.selectedLocation(location)
                }
            )

            Spacer(Modifier.height(32.dp))

            when(val state = uiState) {
               is WeatherUiState.Loading -> {
                   LoadingIndicator()
               }
                is WeatherUiState.Success -> {
                    WeatherInfoDisplay(state.weatherInfo)
                }
                is WeatherUiState.Failure -> {
                    Text(
                        state.errorMessage,
                        color = Color.Red
                    )
                }
            }
        }
    }
}