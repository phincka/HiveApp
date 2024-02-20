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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.hiveapp.data.model.WeatherModel
import com.example.hiveapp.ui.theme.Typography

@Composable
fun TodayWeather(today: WeatherModel) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Puck",
            style = Typography.headlineMedium,
            fontWeight = FontWeight(600),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )

        Text(
            text = today.time.toString(),
            style = Typography.titleSmall,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )

        Text(
            text = today.weatherType.weatherDesc,
            style = Typography.titleSmall,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )

        Image(
            painter = painterResource(today.weatherType.iconRes),
            contentDescription = "weather icon",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(100.dp)
        )

        Text(
            text = today.temperatureCelsius.toString(),
            style = Typography.headlineMedium,
            fontWeight = FontWeight(700),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )

        Text(
            text = "5:50 -> 17:32",
            style = Typography.titleSmall,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}