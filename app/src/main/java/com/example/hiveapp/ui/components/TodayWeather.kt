package com.example.hiveapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.hiveapp.data.model.WeatherModel
import com.example.hiveapp.ui.theme.Typography
import kotlinx.datetime.LocalDateTime

@Composable
fun TodayWeather(today: WeatherModel) {
    val localDateTime = LocalDateTime.parse(today.time)
    val year = localDateTime.year
    val month = localDateTime.monthNumber
    val day = localDateTime.dayOfMonth
    val hour = localDateTime.hour
    val minute = localDateTime.minute
    val parsedDate = "${day}-${month}-${year} | ${hour}:${minute}"

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "[NAZWA_MIASTA]",
            style = Typography.headlineMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )

        Text(
            text = parsedDate,
            style = Typography.titleSmall,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )

        Text(
            text = stringResource(today.weatherType.weatherDesc),
            style = Typography.titleSmall,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )

        Image(
            painter = painterResource(today.weatherType.iconRes),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(100.dp)
        )

        Text(
            text = today.temperatureCelsius.toString(),
            style = Typography.headlineMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}