package com.example.weatherapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.weatherapp.model.Forecastday
import com.example.weatherapp.model.WeatherClass
import com.example.weatherapp.ui.theme.Blue


@Composable
fun ForecastList(weatherData: WeatherClass) {
    val showDialog = remember { mutableStateOf(false) }
    val selectedWeather = remember { mutableStateOf(WeatherClass()) }
    val searchQuery = remember { mutableStateOf("") }
    val weatherList = weatherData.forecast?.forecastday ?: listOf()

    Column(modifier = Modifier.fillMaxWidth()) {
        LazyRow(modifier = Modifier.weight(1f)) {
            itemsIndexed(weatherList) { index, weather ->
                if (weatherData.location?.name?.contains(searchQuery.value, ignoreCase = true) == true) {
                    ForecastListItem(weather = weather) {
                        selectedWeather.value = weatherData
                        showDialog.value = true
                    }
                    if (index < weatherList.size - 1) {
                        Divider(color = Color.LightGray, thickness = 1.dp)
                    }
                }
                if (showDialog.value) {
                    WeatherDialog(weather = weather) {
                        showDialog.value = false
                    }
                }
            }
        }


    }
}

@Composable
fun ForecastListItem(weather: Forecastday, onItemClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .height(200.dp)
            .width(180.dp)
            .clickable { onItemClick.invoke() },
        elevation = 4.dp,
        shape = RoundedCornerShape(size = 16.dp)
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            weather.day.condition.icon.let {
                Icon(
                    painter = rememberAsyncImagePainter(model = "https:${it}"),
                    contentDescription = null,
                    modifier = Modifier.size(48.dp),
                    tint = MaterialTheme.colors.primary
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                weather.day.condition.text.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.h6,
                        color = MaterialTheme.colors.primary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                weather.day.avgtemp_c.let {
                    Text(
                        text = "${it.toInt()} °C",
                        style = MaterialTheme.typography.h6,
                        color = MaterialTheme.colors.Blue,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                weather.day.daily_will_it_rain.let {
                    Text(
                        text = if(it > 0)"Rainy" else "No rain",
                        style = MaterialTheme.typography.subtitle1,
                        color = MaterialTheme.colors.primary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis

                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "min t ${weather.day.mintemp_c.toInt()}°C",
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.primary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "max t ${weather.day.maxtemp_c.toInt()}°C",
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.primary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = weather.date,
                    style = MaterialTheme.typography.body2,
                    color = Color.Gray,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}