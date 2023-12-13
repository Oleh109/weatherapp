package com.example.weatherapp.di

import com.example.weatherapp.constants.Const.BASE_URL
import com.example.weatherapp.repo.WeatherRepo
import com.example.weatherapp.service.WeatherInstance
import com.example.weatherapp.viewmodel.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val weatherModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherInstance::class.java)
    }

    single { WeatherRepo(get()) }

    viewModel { WeatherViewModel(get()) }
}