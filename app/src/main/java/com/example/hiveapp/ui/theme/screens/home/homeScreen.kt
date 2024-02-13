package com.example.hiveapp.ui.theme.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
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
import com.example.hiveapp.ui.components.HivesLazyColumn
import com.example.hiveapp.ui.components.TopBar
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val homeViewModel: HomeViewModel = getViewModel()

    val hives by homeViewModel.getAll.collectAsState(emptyList())

    var isDropdownMenuVisible by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopBar(
                navController,
                scrollBehavior,
                title = stringResource(R.string.home_top_bar_title),
                content = {
                    IconButton(onClick = { isDropdownMenuVisible = true }) {
                        Icon(
                            imageVector = Icons.Filled.Search,
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
            Column {
                if (hives.isNotEmpty()) {
                    HivesLazyColumn(hives, navController)
                } else {
                    Text(stringResource(R.string.home_no_hives))
                }
            }
        }
    }
}
