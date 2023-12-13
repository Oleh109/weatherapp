package com.example.weatherapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.weatherapp.model.Forecastday


@Composable
fun WeatherDialog(weather: Forecastday, onCloseDialog: () -> Unit) {
    Dialog(onDismissRequest = { onCloseDialog.invoke() }) {
        Surface(
            modifier = Modifier.padding(16.dp),
            shape = MaterialTheme.shapes.medium,
            elevation = 8.dp
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                weather.day.condition.text.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.padding(16.dp)
                    )
                }
                weather.day.avgtemp_c.let {
                    Text(
                        text = "Average temp: ${it.toInt()} °C",
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.padding(16.dp)
                    )
                }
                weather.day.daily_will_it_rain.let {
                    Text(
                        text = if (it > 0) "Rainy" else "No rain",
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.padding(16.dp)
                    )
                }
                Divider(color = Color.LightGray, thickness = 1.dp)
                Text(
                    text = "min t ${weather.day.mintemp_c.toInt()}°C",
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(16.dp)
                )
                Text(
                    text = "max t ${weather.day.maxtemp_c.toInt()}°C",
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(16.dp)
                )
                Text(
                    text = "Wind: ${weather.day.avgvis_km}",
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(16.dp)
                )
                Text(
                    text = "Humidity: ${weather.day.avghumidity}",
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(16.dp)
                )
                Text(
                    text = "Sunrise: ${weather.astro.sunrise}",
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(16.dp)
                )
                Text(
                    text = "Sunset: ${weather.astro.sunset}",
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(16.dp)
                )
                Button(
                    onClick = { onCloseDialog.invoke() },
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.End)
                ) {
                    Text(text = "Close")
                }
            }
        }
    }
}
