package com.example.hiveapp.ui.theme.screens.hive

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.hiveapp.R
import com.example.hiveapp.data.util.DropdownMenuItemData
import com.example.hiveapp.notifications.NotificationService
import com.example.hiveapp.ui.components.Dropdown
import com.example.hiveapp.ui.components.Modal
import com.example.hiveapp.ui.components.TextButton
import com.example.hiveapp.ui.components.TopBar
import com.example.hiveapp.ui.theme.Typography
import com.example.hiveapp.ui.theme.screens.destinations.HomeScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.result.ResultBackNavigator
import org.koin.androidx.compose.get
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun HiveScreen(
    id: String,
    navigator: DestinationsNavigator,
    resultNavigator: ResultBackNavigator<Boolean>
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val hiveViewModel: HiveViewModel = koinViewModel()
    val hiveState by hiveViewModel.hiveState.collectAsState()

    val notificationService = get<NotificationService>()

    var isDropdownMenuVisible by remember { mutableStateOf(false) }
    var isModalActive by remember { mutableStateOf(false) }

    var locationButtonTextId by remember { mutableIntStateOf(R.string.hive_nav_add_geo) }
    var lat by remember { mutableDoubleStateOf(54.749054) }
    var lng by remember { mutableDoubleStateOf(18.3732243) }

    val menuItems = listOf(
        DropdownMenuItemData(
            icon = Icons.Outlined.Edit,
            text = stringResource(R.string.hive_nav_add_geo),
            onClick = {
//                navigator.navigate(
//                    AddHiveLocationDestination(id, lat, lng)
//                )
            }
        ),
        DropdownMenuItemData(
            icon = Icons.Outlined.Edit,
            text = stringResource(R.string.hive_nav_edit_hive),
            onClick = { }
        ),
        DropdownMenuItemData(
            icon = Icons.Outlined.Clear,
            text = stringResource(R.string.hive_nav_remove_hive),
            onClick = {
                isModalActive = true
            }
        ),
    )

    if (hiveViewModel.removeState) {
        navigator.navigate(
            HomeScreenDestination
        )
    }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopBar(
                backNavigation = { resultNavigator.navigateBack(result = true) },
                scrollBehavior = scrollBehavior,
                title = stringResource(R.string.hive_top_bar_title),
                content = {
                    IconButton(onClick = { isDropdownMenuVisible = true }) {
                        Icon(
                            imageVector = Icons.Filled.MoreVert,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        val topPadding = innerPadding.calculateTopPadding() + 12.dp
        val horizontalPadding = 24.dp

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = horizontalPadding,
                    end = horizontalPadding,
                    top = topPadding
                ),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            when (hiveState) {
                is HiveState.Success -> {
                    val hive = (hiveState as HiveState.Success).hive

                    if (hive.lat > 0 && hive.lng > 0) {
                        lat = hive.lat
                        lng = hive.lng
                        locationButtonTextId = R.string.hive_nav_update_geo
                    } else {
                        notificationService.showGeoNotification()
                    }

                    Text(
                        text = hive.name,
                        style = Typography.titleLarge,
                    )

                    TextButton(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(locationButtonTextId),
                        onClick = {
//                            navigator.navigate(
//                                AddHiveLocationDestination(id, lat, lng)
//                            )
                        }
                    )

                    TextButton(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(R.string.hive_nav_show_weather),
                        onClick = {
//                            navigator.navigate(
//                                WeatherScreenDestination(id)
//                            )
                        }
                    )

                    Dropdown(
                        isDropdownMenuVisible = isDropdownMenuVisible,
                        setDropdownMenuVisible = { isDropdownMenuVisible = it },
                        menuItems = menuItems
                    )

                    Modal(
                        dialogTitle = stringResource(R.string.hive_remove_modal_title),
                        dialogText = stringResource(R.string.hive_remove_modal_text),
                        icon = Icons.Filled.Warning,
                        isModalActive = isModalActive,
                        onDismissRequest = { isModalActive = false },
                        onConfirmation = {
                            hiveViewModel.removeHive(listOf(hive))
                        },
                    )
                }

                is HiveState.Error -> {
                    val errorMessage = (hiveState as HiveState.Error).message
                    Text(errorMessage)
                }

                is HiveState.Loading -> {
                    Text(stringResource(R.string.home_loading))
                }
            }
        }
    }
}