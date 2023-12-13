package com.example.weatherapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.model.WeatherClass
import com.example.weatherapp.ui.theme.Blue

@Composable
fun WeatherView(
    weatherData: WeatherClass,
    searchText: String,
    onSearchTextChange: (String) -> Unit,
    onSearchSubmit: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        SearchBar(searchText, onSearchTextChange, onSearchSubmit)
        ForecastList(
            weatherData
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Bottom
            ) {
                weatherData.location?.country?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.h4,
                        color = MaterialTheme.colors.primary
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = weatherData.location?.region.takeIf { it?.isNotEmpty() == true }
                        ?: "Region not provided",
                    style = MaterialTheme.typography.h4,
                    color = MaterialTheme.colors.primary
                )
                Spacer(modifier = Modifier.height(8.dp))
                weatherData.location?.name?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.h4,
                        color = MaterialTheme.colors.primary
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                weatherData.current?.condition?.text?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.subtitle1,
                        color = MaterialTheme.colors.Blue
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "${weatherData.current?.temp_c?.toInt()}°C",
                    style = MaterialTheme.typography.h2,
                    color = MaterialTheme.colors.Blue
                )
            }
        }
        Divider(
            color = Color.Gray,
            modifier = Modifier
                .padding(top = 16.dp, bottom = 16.dp)
                .fillMaxWidth()
                .height(1.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            WeatherDetailItem(
                icon = R.drawable.ic_humidity,
                label = "Humidity",
                value = "${weatherData.current?.humidity.toString()} %"
            )
            WeatherDetailItem(
                icon = R.drawable.ic_wind,
                label = "Wind Speed",
                value = "${weatherData.current?.wind_kph.toString()} Kph"
            )
            WeatherDetailItem(
                icon = R.drawable.ic_cloud,
                label = "Cloudiness",
                value = "${weatherData.current?.cloud.toString()} %"
            )
        }
    }
}

@Composable
fun WeatherDetailItem(icon: Int, label: String, value: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = MaterialTheme.colors.primary,
            modifier = Modifier.size(48.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.primary
        )
        Text(
            text = value,
            style = MaterialTheme.typography.subtitle1,
            color = MaterialTheme.colors.Blue
        )
    }
}