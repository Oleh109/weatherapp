package com.example.weatherapp.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.example.weatherapp.viewmodel.WeatherState
import com.example.weatherapp.viewmodel.WeatherViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(weatherViewModel: WeatherViewModel = koinViewModel()) {
    val searchText by weatherViewModel.searchText.collectAsState()
    val weatherState by weatherViewModel.weatherState.observeAsState()

    when (val state = weatherState) {
        is WeatherState.Loading -> {
            LoadingScreen()
        }
        is WeatherState.Result -> {
            WeatherView(state.weatherData, searchText, weatherViewModel::onSearchTextChange, weatherViewModel::onSearchSubmit )
        }
        is WeatherState.Error -> {
            NoResultsScreen(state.message)
        }
        else -> {}
    }
}

