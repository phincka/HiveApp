package com.example.hiveapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
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
fun WeatherCard(weather: WeatherModel, modifier: Modifier) {
    Card(
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
        ) {
            Text(
                text = weather.time.toString(),
                textAlign = TextAlign.Center,
                style = Typography.titleSmall,
                modifier = Modifier.fillMaxWidth()
            )

            Image(
                painter = painterResource(weather.weatherType.iconRes),
                contentDescription = "cloudy_day",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(50.dp)
            )

            Text(
                text = weather.temperatureCelsius.toString(),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight(600),
                style = Typography.titleSmall,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            )
        }
    }
}

