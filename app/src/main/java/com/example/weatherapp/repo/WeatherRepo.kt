package com.example.weatherapp.repo

import com.example.weatherapp.service.WeatherInstance

open class WeatherRepo(private val instance: WeatherInstance) {
    suspend fun getCurrentWeather(key: String, query: String, days: Int) =
        instance.getCurrentTemp(key, query, days)
}