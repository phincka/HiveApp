package com.example.hiveapp.ui.theme.screens.hive

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Edit
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.hiveapp.R
import com.example.hiveapp.data.model.Hive
import com.example.hiveapp.ui.components.Dropdown
import com.example.hiveapp.ui.components.Modal
import com.example.hiveapp.ui.components.TopBar
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HiveScreen(navController: NavController, id: Int) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val hiveViewModel: HiveViewModel = getViewModel()

    val hiveList by hiveViewModel.getHive(id).collectAsState(initial = emptyList())
    val hive = hiveList.firstOrNull()

    println(hive)

    var isDropdownMenuVisible by remember { mutableStateOf(false) }
    var isModalActive by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopBar(
                navController,
                scrollBehavior,
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
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = {},
                text = { Text(stringResource(R.string.home_floating_action_button)) },
                icon = {
                    Icon(
                        Icons.Filled.Add,
                        contentDescription = null
                    )
                },
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(top = 10.dp)
        ) {
            if (hive !== null) {
                Text(hive.name)
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
                onClick = { },
                leadingIcon = {
                    Icon(
                        Icons.Outlined.Edit,
                        contentDescription = null
                    )
                }
            )
            DropdownMenuItem(
                text = { Text(stringResource(R.string.hive_nav_edit_hive)) },
                onClick = { navController.navigate("${Screen.CreateEditHive.route}/${id}") },
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