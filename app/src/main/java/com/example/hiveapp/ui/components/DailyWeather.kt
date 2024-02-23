package com.example.hiveapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.hiveapp.R
import com.example.hiveapp.data.model.WeatherModel
import com.example.hiveapp.ui.theme.Typography

@Composable
fun DailyWeather(dailyData: List<Map.Entry<Int, List<WeatherModel>>>) {
    Text(
        text = stringResource(R.string.weather_screen_daily),
        style = Typography.titleMedium,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp)
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        for (data in dailyData.take(4)) {
            WeatherCard(data.value[12], Modifier.weight(1f))
        }
    }
}