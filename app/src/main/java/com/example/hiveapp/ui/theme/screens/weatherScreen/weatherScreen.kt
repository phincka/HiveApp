package com.example.hiveapp.ui.theme.screens.weatherScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.hiveapp.R
import com.example.hiveapp.notifications.NotificationService
import com.example.hiveapp.ui.components.TopBar
import com.example.hiveapp.ui.theme.Typography
import com.example.hiveapp.ui.theme.screens.hive.HiveViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherScreen(navController: NavController, id: Int) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val hiveViewModel: HiveViewModel = getViewModel()

    val hiveList by hiveViewModel.getHive(id).collectAsState(initial = emptyList())
    val hive = hiveList.firstOrNull()

    val notificationService = get<NotificationService>()

    if (hive !== null && hive.lat == 0.0) {
        LaunchedEffect(Unit) {
            notificationService.showGeoNotification()
        }
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
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            if (hive !== null) {
                Spacer(modifier = Modifier.height(16.dp))

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Puck",
                        style = Typography.headlineMedium,
                        fontWeight = FontWeight(600),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                    )

                    Text(
                        text = "15.02.2024",
                        style = Typography.titleSmall,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                    )

                    Image(
                        painter = painterResource(R.drawable.cloudy_day),
                        contentDescription = "cloudy_day",
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .size(100.dp)
                    )

                    Text(
                        text = "15 .C",
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
                                    text = "Wiatr",
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.fillMaxWidth()
                                )

                                Image(
                                    painter = painterResource(R.drawable.cloudy_day),
                                    contentDescription = "cloudy_day",
                                    modifier = Modifier
                                        .align(Alignment.CenterHorizontally)
                                        .size(50.dp)
                                )

                                Text(
                                    text = "13",
                                    textAlign = TextAlign.Center,
                                    fontWeight = FontWeight(600),
                                    style = Typography.titleLarge,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 8.dp)
                                )

                                Text(
                                    text = "km/h",
                                    textAlign = TextAlign.Center,
                                    style = Typography.bodySmall,
                                    modifier = Modifier.fillMaxWidth()
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
                                    text = "Wiatr",
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.fillMaxWidth()
                                )

                                Image(
                                    painter = painterResource(R.drawable.cloudy_day),
                                    contentDescription = "cloudy_day",
                                    modifier = Modifier
                                        .align(Alignment.CenterHorizontally)
                                        .size(50.dp)
                                )

                                Text(
                                    text = "13",
                                    textAlign = TextAlign.Center,
                                    fontWeight = FontWeight(600),
                                    style = Typography.titleLarge,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 8.dp)
                                )

                                Text(
                                    text = "km/h",
                                    textAlign = TextAlign.Center,
                                    style = Typography.bodySmall,
                                    modifier = Modifier.fillMaxWidth()
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
                                    text = "Wiatr",
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.fillMaxWidth()
                                )

                                Image(
                                    painter = painterResource(R.drawable.cloudy_day),
                                    contentDescription = "cloudy_day",
                                    modifier = Modifier
                                        .align(Alignment.CenterHorizontally)
                                        .size(50.dp)
                                )

                                Text(
                                    text = "13",
                                    textAlign = TextAlign.Center,
                                    fontWeight = FontWeight(600),
                                    style = Typography.titleLarge,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 8.dp)
                                )

                                Text(
                                    text = "km/h",
                                    textAlign = TextAlign.Center,
                                    style = Typography.bodySmall,
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                        }
                    }

                    Text(
                        text = "Dziś",
                        style = Typography.titleMedium,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 24.dp)
                    )

                    SliderDemo()
                }
            } else {
                Text(stringResource(R.string.home_no_hive))
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
@Preview(showSystemUi = true)
fun Preview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Puck",
            style = Typography.headlineMedium,
            fontWeight = FontWeight(600),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )

        Text(
            text = "15.02.2024",
            style = Typography.titleSmall,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )

        Image(
            painter = painterResource(R.drawable.cloudy_day),
            contentDescription = "cloudy_day",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(100.dp)
        )

        Text(
            text = "15 .C",
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
                        text = "Wiatr",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Image(
                        painter = painterResource(R.drawable.cloudy_day),
                        contentDescription = "cloudy_day",
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .size(50.dp)
                    )

                    Text(
                        text = "13",
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight(600),
                        style = Typography.titleLarge,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                    )

                    Text(
                        text = "km/h",
                        textAlign = TextAlign.Center,
                        style = Typography.bodySmall,
                        modifier = Modifier.fillMaxWidth()
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
                        text = "Wiatr",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Image(
                        painter = painterResource(R.drawable.cloudy_day),
                        contentDescription = "cloudy_day",
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .size(50.dp)
                    )

                    Text(
                        text = "13",
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight(600),
                        style = Typography.titleLarge,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                    )

                    Text(
                        text = "km/h",
                        textAlign = TextAlign.Center,
                        style = Typography.bodySmall,
                        modifier = Modifier.fillMaxWidth()
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
                        text = "Wiatr",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Image(
                        painter = painterResource(R.drawable.cloudy_day),
                        contentDescription = "cloudy_day",
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .size(50.dp)
                    )

                    Text(
                        text = "13",
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight(600),
                        style = Typography.titleLarge,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                    )

                    Text(
                        text = "km/h",
                        textAlign = TextAlign.Center,
                        style = Typography.bodySmall,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }

        Text(
            text = "Dziś",
            style = Typography.titleMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
        )

        val pagerState = rememberPagerState(initialPage = 0)

        LaunchedEffect(Unit) {
            while (true) {
                yield()
                delay(2600)
                pagerState.animateScrollToPage(
                    page = (pagerState.currentPage + 1) % (pagerState.pageCount)
                )
            }
        }

        SliderDemo()
    }
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun SliderDemo() {
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
        count = pages.size,
        state = pagerState,
        itemSpacing = 5.dp,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        page ->

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
                        text = pages[page][0],
                        textAlign = TextAlign.Center,
                        style = Typography.titleSmall,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Image(
                        painter = painterResource(R.drawable.cloudy_day),
                        contentDescription = "cloudy_day",
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .size(50.dp)
                    )

                    Text(
                        text = "13 .C",
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
                        text = pages[page][1],
                        textAlign = TextAlign.Center,
                        style = Typography.titleSmall,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Image(
                        painter = painterResource(R.drawable.cloudy_day),
                        contentDescription = "cloudy_day",
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .size(50.dp)
                    )

                    Text(
                        text = "13 .C",
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
                        text = pages[page][2],
                        textAlign = TextAlign.Center,
                        style = Typography.titleSmall,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Image(
                        painter = painterResource(R.drawable.cloudy_day),
                        contentDescription = "cloudy_day",
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .size(50.dp)
                    )

                    Text(
                        text = "13 .C",
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
                        text = pages[page][3],
                        textAlign = TextAlign.Center,
                        style = Typography.titleSmall,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Image(
                        painter = painterResource(R.drawable.cloudy_day),
                        contentDescription = "cloudy_day",
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .size(50.dp)
                    )

                    Text(
                        text = "13 .C",
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
        modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
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
