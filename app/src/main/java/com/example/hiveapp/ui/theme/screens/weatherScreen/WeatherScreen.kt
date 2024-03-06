package com.example.hiveapp.ui.theme.screens.weatherScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.hiveapp.R
import com.example.hiveapp.ui.components.DailyWeather
import com.example.hiveapp.ui.components.HourlyWeatherSlider
import com.example.hiveapp.ui.components.TodayWeather
import com.example.hiveapp.ui.components.TopBar
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.result.ResultBackNavigator
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun WeatherScreen(
    id: Int,
    resultNavigator: ResultBackNavigator<Boolean>,
    weatherViewModel: WeatherViewModel = koinViewModel(parameters = { parametersOf(id) })
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val weatherState = weatherViewModel.weatherState.collectAsState().value

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopBar(
                backNavigation = { resultNavigator.navigateBack(result = true) },
                scrollBehavior,
                title = stringResource(R.string.weather_top_title),
                content = { }
            )
        }
    ) { innerPadding ->
        val topPadding = innerPadding.calculateTopPadding() + 12.dp
        val bottomPadding = 24.dp
        val horizontalPadding = 24.dp

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = horizontalPadding,
                    end = horizontalPadding,
                    top = topPadding,
                    bottom = bottomPadding
                )
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            when (weatherState) {
                is WeatherState.Success -> {
                    val today = weatherState.today
                    val hourly = weatherState.hourly
                    val daily = weatherState.daily

                    TodayWeather(today)
                    HourlyWeatherSlider(hourly)
                    DailyWeather(daily)
                }
                is WeatherState.Error -> {
                    val errorMessage = weatherState.message
                    Text(errorMessage)
                }
                is WeatherState.Info -> {
                    val message = weatherState.message
                    Text(message)
                }
                is WeatherState.Loading -> {
                    Text(stringResource(R.string.home_loading))
                }
            }
        }
    }
}