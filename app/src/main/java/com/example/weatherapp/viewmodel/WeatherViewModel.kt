package com.example.weatherapp.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.constants.Const.API_KEY
import com.example.weatherapp.model.WeatherClass
import com.example.weatherapp.repo.WeatherRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

sealed class WeatherState {
    object Loading : WeatherState()
    data class Result(val weatherData: WeatherClass) : WeatherState()
    data class Error(val message: String) : WeatherState()
}

open class WeatherViewModel(private val repository: WeatherRepo) : ViewModel() {
    private val _weatherState = MutableLiveData<WeatherState>()
    val weatherState: LiveData<WeatherState> = _weatherState

    private val _searchText = MutableStateFlow("")
    val searchText: StateFlow<String> = _searchText.asStateFlow()

    @SuppressLint("VisibleForTests")
    private fun getCurrentWeather(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _weatherState.postValue(WeatherState.Loading)
            val response = repository.getCurrentWeather(API_KEY, query, 16)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val weatherData = response.body()
                    _weatherState.postValue(weatherData?.let { WeatherState.Result(it) })
                } else {
                    val errorMessage = "Error fetching data"
                    _weatherState.postValue(WeatherState.Error(errorMessage))
                    Log.d("Tag", errorMessage)
                }
            }
        }
    }

    fun getWeather(cityName: String) {
        val query = _searchText.value.ifEmpty { cityName }
        getCurrentWeather(query)
    }

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    fun onSearchSubmit(){
        getWeather(_searchText.value)
    }
}
