package com.example.weatherapp.service

import com.example.weatherapp.model.WeatherClass
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherInstance {
    @GET("forecast.json")
    suspend fun getCurrentTemp(
        @Query("key") key: String,
        @Query("q") query: String,
        @Query("days") days: Int,
    ): Response<WeatherClass>
}