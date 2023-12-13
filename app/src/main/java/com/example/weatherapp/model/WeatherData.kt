package com.example.weatherapp.model

data class WeatherClass(
    val current: Current? = null,
    val forecast: Forecast? = null,
    val location: Location? = null
)

data class Location(
    val country: String,
    val localtime: String,
    val name: String,
    val region: String,
)

data class Forecast(
    val forecastday: List<Forecastday>
)

data class Forecastday(
    val astro: Astro,
    val date: String,
    val day: Day
)

data class Astro(
    val sunrise: String,
    val sunset: String
)

data class Condition(
    val icon: String,
    val text: String
)
data class ConditionX(
    val icon: String,
    val text: String
)

data class Current(
    val cloud: Int,
    val condition: Condition,
    val humidity: Int,
    val temp_c: Double,
    val wind_degree: Int,
    val wind_kph: Double,
)

data class Day(
    val avghumidity: Double,
    val avgtemp_c: Double,
    val avgvis_km: Double,
    val condition: ConditionX,
    val daily_will_it_rain: Int,
    val maxtemp_c: Double,
    val maxwind_kph: Double,
    val mintemp_c: Double,
    val totalprecip_mm: Double,
)
