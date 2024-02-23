package com.example.hiveapp.ui.theme.screens.weatherScreen

import Screen
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.hiveapp.R
import com.example.hiveapp.ui.components.DailyWeather
import com.example.hiveapp.ui.components.HourlyWeatherSlider
import com.example.hiveapp.ui.components.TextButton
import com.example.hiveapp.ui.components.TodayWeather
import com.example.hiveapp.ui.components.TopBar
import com.example.hiveapp.ui.theme.Typography
import org.koin.androidx.compose.koinViewModel

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherScreen(navController: NavController, id: Int) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val weatherViewModel: WeatherViewModel = koinViewModel()

    val hiveList by weatherViewModel.getLocationByHiveId(id).collectAsState(initial = emptyList())
    val hive = hiveList.firstOrNull()

    if (hive != null && hive.lat > 0.0 && hive.lng > 0.0) {
        weatherViewModel.getWeather(hive.lat, hive.lng)
    }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopBar(
                navController,
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
            if (weatherViewModel.weatherState.loading == false) {
                if (weatherViewModel.weatherState.today !== null) {
                    TodayWeather(weatherViewModel.weatherState.today!!)
                }
                if (weatherViewModel.weatherState.hourly !== null) {
                    val hourly = weatherViewModel.weatherState.hourly!!
                    HourlyWeatherSlider(hourly)
                }
                if (weatherViewModel.weatherState.daily !== null) {
                    val daily = weatherViewModel.weatherState.daily!!
                    DailyWeather(daily)
                }
            } else if (hive != null && hive.lat == 0.0 && hive.lng == 0.0) {
                Text(
                    text = stringResource(R.string.weather_screen_no_location),
                    style = Typography.titleMedium,
                )

                TextButton(
                    modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
                    text = stringResource(R.string.hive_nav_add_geo),
                    onClick = { navController.navigate("${Screen.AddHiveLocation.route}/${id}?lat=${54.749054}&lng=${18.3732243}") }
                )
            } else {
                Text(
                    text = stringResource(R.string.weather_screen_loading),
                    style = Typography.titleLarge,
                )
            }
        }
    }
}