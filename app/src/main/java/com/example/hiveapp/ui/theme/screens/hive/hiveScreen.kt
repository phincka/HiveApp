package com.example.hiveapp.ui.theme.screens.hive

import Screen
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.hiveapp.R
import com.example.hiveapp.notifications.NotificationService
import com.example.hiveapp.ui.components.Dropdown
import com.example.hiveapp.ui.components.Modal
import com.example.hiveapp.ui.components.TopBar
import com.example.hiveapp.ui.theme.Typography
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HiveScreen(navController: NavController, id: Int) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val hiveViewModel: HiveViewModel = getViewModel()

    val hiveList by hiveViewModel.getHive(id).collectAsState(initial = emptyList())
    val hive = hiveList.firstOrNull()

    val notificationService = get<NotificationService>()
    notificationService.showCreateNotification()

    var isDropdownMenuVisible by remember { mutableStateOf(false) }
    var isModalActive by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopBar(
                navController,
                scrollBehavior,
                title = "${stringResource(R.string.hive_top_bar_title)} ${hive?.name}",
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            if (hive !== null) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = hive.name,
                    style = Typography.titleLarge,
                )

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {navController.navigate("${Screen.AddHiveLocation.route}/${id}")}
                ) {
                    if (hive.lat > 0 && hive.lng > 0) {
                        Text(stringResource(R.string.hive_nav_update_geo))
                    } else {
                        Text(stringResource(R.string.hive_nav_add_geo))
                    }
                }
            } else {
                Text(stringResource(R.string.home_no_hive))
            }
        }

        Dropdown(
            isDropdownMenuVisible = isDropdownMenuVisible,
            setDropdownMenuVisible = {isDropdownMenuVisible = it},
        )
        {
            DropdownMenuItem(
                text = { Text(stringResource(R.string.hive_nav_add_geo)) },
                onClick = { navController.navigate("${Screen.AddHiveLocation.route}/${id}") },
                leadingIcon = {
                    Icon(
                        Icons.Outlined.Edit,
                        contentDescription = null
                    )
                }
            )
            DropdownMenuItem(
                text = { Text(stringResource(R.string.hive_nav_edit_hive)) },
                onClick = { navController.navigate("${Screen.AddHiveLocation.route}/${id}") },
                leadingIcon = {
                    Icon(
                        Icons.Outlined.Edit,
                        contentDescription = null
                    )
                }
            )

            Divider()
            Spacer(modifier = Modifier.height(16.dp))

            DropdownMenuItem(
                text = { Text(stringResource(R.string.hive_nav_remove_hive)) },
                onClick = { isModalActive = true },
                leadingIcon = {
                    Icon(
                        Icons.Outlined.Clear,
                        contentDescription = null
                    )
                }
            )
        }

        Modal(
            dialogTitle = stringResource(R.string.hive_remove_modal_title),
            dialogText = stringResource(R.string.hive_remove_modal_text),
            icon = Icons.Filled.Warning,
            isModalActive = isModalActive,
            onDismissRequest = {isModalActive = false},
            onConfirmation = {
                if (hive != null) {
                    hiveViewModel.removeHive(hive)
                    navController.navigate(Screen.Home.route)
                }
            },
        )
    }
}