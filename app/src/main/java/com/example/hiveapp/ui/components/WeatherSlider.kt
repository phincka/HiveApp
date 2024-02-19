package com.example.hiveapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.hiveapp.data.model.WeatherModel
import com.example.hiveapp.ui.theme.Typography
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun WeatherSlider(weatherData: List<WeatherModel>) {
    val pagerState = rememberPagerState(0)
    val animationScope = rememberCoroutineScope()

    var isPrevEnable = false

    if (pagerState.currentPage > 0) {
        isPrevEnable = true
    }

    val pages = listOf(
        listOf("13:00", "14:00", "15:00", "16:00"),
        listOf("17:00", "18:00", "19:00", "20:00"),
        listOf("21:00", "22:00", "23:00", "24:00"),
    )

    HorizontalPager(
        count = weatherData.size,
        state = pagerState,
        itemSpacing = 5.dp,
        modifier = Modifier
            .fillMaxWidth()
    ) {
            data ->

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Card(
                modifier = Modifier.weight(1f),
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                ) {
                    Text(
                        text = weatherData[data].time.toString(),
                        textAlign = TextAlign.Center,
                        style = Typography.titleSmall,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Image(
                        painter = painterResource(weatherData[data].weatherType.iconRes),
                        contentDescription = "cloudy_day",
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .size(50.dp)
                    )

                    Text(
                        text = weatherData[data].temperatureCelsius.toString(),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight(600),
                        style = Typography.titleSmall,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                    )
                }
            }
            Card(
                modifier = Modifier.weight(1f),
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                ) {
                    Text(
                        text = weatherData[data].time.toString(),
                        textAlign = TextAlign.Center,
                        style = Typography.titleSmall,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Image(
                        painter = painterResource(weatherData[data].weatherType.iconRes),
                        contentDescription = "cloudy_day",
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .size(50.dp)
                    )

                    Text(
                        text = weatherData[data].temperatureCelsius.toString(),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight(600),
                        style = Typography.titleSmall,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                    )
                }
            }
            Card(
                modifier = Modifier.weight(1f),
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                ) {
                    Text(
                        text = weatherData[data].time.toString(),
                        textAlign = TextAlign.Center,
                        style = Typography.titleSmall,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Image(
                        painter = painterResource(weatherData[data].weatherType.iconRes),
                        contentDescription = "cloudy_day",
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .size(50.dp)
                    )

                    Text(
                        text = weatherData[data].temperatureCelsius.toString(),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight(600),
                        style = Typography.titleSmall,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                    )
                }
            }
            Card(
                modifier = Modifier.weight(1f),
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                ) {
                    Text(
                        text = weatherData[data].time.toString(),
                        textAlign = TextAlign.Center,
                        style = Typography.titleSmall,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Image(
                        painter = painterResource(weatherData[data].weatherType.iconRes),
                        contentDescription = "cloudy_day",
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .size(50.dp)
                    )

                    Text(
                        text = weatherData[data].temperatureCelsius.toString(),
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
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Button(
            enabled = isPrevEnable,
            onClick = {
                animationScope.launch {
                    pagerState.animateScrollToPage(pagerState.currentPage - 1)
                }
            },
            modifier = Modifier.weight(1f),
        ) {
            Text(text = "Cofnij")
        }

        Button(
            onClick = {
                animationScope.launch {
                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                }
            },
            modifier = Modifier.weight(1f),
        ) {
            Text(text = "Pokaż następne")
        }
    }
}
