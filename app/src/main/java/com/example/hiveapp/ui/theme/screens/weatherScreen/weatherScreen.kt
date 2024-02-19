package com.example.hiveapp.ui.theme.screens.weatherScreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.hiveapp.R
import com.example.hiveapp.ui.components.TopBar
import com.example.hiveapp.ui.components.WeatherSlider
import com.example.hiveapp.ui.theme.Typography
import org.koin.androidx.compose.getViewModel

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherScreen(navController: NavController, id: Int) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val weatherViewModel: WeatherViewModel = getViewModel()

    val hiveList by weatherViewModel.getLocationByHiveId(id).collectAsState(initial = emptyList())
    val hive = hiveList.firstOrNull()


    if (hive != null) {
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            if (weatherViewModel.state.loading == false) {
                Spacer(modifier = Modifier.height(16.dp))

                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Spacer(modifier = Modifier.height(16.dp))

                    if (weatherViewModel.state.today !== null) {
                        val today = weatherViewModel.state.today!!

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

                    if (weatherViewModel.state.hourly !== null) {
                        val hourly = weatherViewModel.state.hourly!!
                        Text(
                            text = "Dziś",
                            style = Typography.titleMedium,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 24.dp)
                        )

                        WeatherSlider(hourly)
                    }

                    if (weatherViewModel.state.daily !== null) {
                        val daily = weatherViewModel.state.daily!!
                        Text(
                            text = "Najbliższe dni",
                            style = Typography.titleMedium,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 24.dp)
                        )

                        WeatherSlider(daily)
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                }
            } else {
                Text(
                    text = "Ładowanie....",
                    style = Typography.titleLarge,
                    fontWeight = FontWeight(700)
                )
            }
        }
    }
}