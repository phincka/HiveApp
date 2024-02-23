package com.example.hiveapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.hiveapp.R
import com.example.hiveapp.data.model.WeatherModel
import com.example.hiveapp.ui.theme.Typography
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@Composable
fun HourlyWeatherSlider(hourlyData: List<WeatherModel>) {
    val pagerState = rememberPagerState(0)
    val animationScope = rememberCoroutineScope()

    var isPrevEnable = false

    if (pagerState.currentPage > 0) {
        isPrevEnable = true
    }

    fun groupByItems(weatherList: List<WeatherModel>, groupIndex: Int): List<List<WeatherModel>> {
        val grouped = mutableListOf<List<WeatherModel>>()

        var startIndex = 0
        var endIndex = minOf(groupIndex, weatherList.size)

        while (startIndex < weatherList.size) {
            val singleList = weatherList.subList(startIndex, endIndex)
            grouped.add(singleList)

            startIndex = endIndex
            endIndex = minOf(endIndex + groupIndex, weatherList.size)
        }

        return grouped
    }

    val groupedTables = groupByItems(hourlyData.toList(), 4)

    Text(
        text = stringResource(R.string.weather_screen_today),
        style = Typography.titleMedium,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp)
    )

    HorizontalPager(
        count = groupedTables.size,
        state = pagerState,
        itemSpacing = 8.dp,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        data ->

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            for (index in groupedTables[data].indices) {
                WeatherCard(groupedTables[data][index], Modifier.weight(1f))
            }
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(24.dp)
    ) {

        TextButton(
            modifier = Modifier.weight(1f),
            text = stringResource(R.string.weather_screen_today_prev),
            onClick = {
                animationScope.launch {
                    pagerState.animateScrollToPage(pagerState.currentPage - 1)
                }
            },
            enabled = isPrevEnable,
        )


        TextButton(
            modifier = Modifier.weight(1f),
            text = stringResource(R.string.weather_screen_today_next),
            onClick = {
                animationScope.launch {
                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                }
            }
        )
    }
}
