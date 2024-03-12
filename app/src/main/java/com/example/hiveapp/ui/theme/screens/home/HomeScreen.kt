package com.example.hiveapp.ui.theme.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Add
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.hiveapp.R
import com.example.hiveapp.data.util.AuthState
import com.example.hiveapp.ui.components.HivesLazyColumn
import com.example.hiveapp.ui.components.LoadingDialog
import com.example.hiveapp.ui.components.TextError
import com.example.hiveapp.ui.components.TopBar
import com.example.hiveapp.ui.theme.screens.destinations.CreateEditHiveScreenDestination
import com.example.hiveapp.ui.theme.screens.destinations.SignInScreenDestination
import com.example.hiveapp.ui.theme.screens.destinations.VerifyEmailScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class)
@RootNavGraph(start = true)
@Destination()
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator,
    homeViewModel: HomeViewModel = koinViewModel()
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val homeState by homeViewModel.homeState.collectAsState()
    val signOutState = homeViewModel.signOutState.collectAsState()
    val user = homeViewModel.user.value

//    println("--------------")
//    println(stringResource(R.string.default_lat).toDouble())
//    println(stringResource(R.string.default_lng).toDouble())
//    println("--------------")


    if (user == null) {
        navigator.navigate(SignInScreenDestination)
    } else {
        if (!user.isEmailVerified) navigator.navigate(VerifyEmailScreenDestination)
    }

    when(signOutState.value) {
        is AuthState.Loading -> LoadingDialog(stringResource(R.string.home_loading))

        is AuthState.Success -> {
            val success = (signOutState.value as AuthState.Success).success
            if (success) navigator.navigate(SignInScreenDestination)
        }

        is AuthState.Error ->  {
            val errorMessage = (signOutState.value as AuthState.Error).error
            Text(errorMessage)
        }
    }

    Scaffold(
        topBar = {
            TopBar(
                scrollBehavior = scrollBehavior,
                title = stringResource(R.string.home_top_bar_title),
                content = {
                    IconButton(
                        onClick = {
                            homeViewModel.signOut()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                            contentDescription = null
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = {
                    navigator.navigate(
                        CreateEditHiveScreenDestination
                    )
                },
                text = { Text(stringResource(R.string.home_floating_action_button)) },
                icon = {
                    Icon(
                        Icons.Filled.Add,
                        contentDescription = null
                    )
                },
            )
        },

    ) { innerPadding ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
        ) {
            when (homeState) {
                is HomeState.Success -> {
                    val hives = (homeState as HomeState.Success).hives
                    HivesLazyColumn(hives, navigator)
                }
                is HomeState.Error -> {
                    val errorMessage = (homeState as HomeState.Error).message
                    TextError(errorMessage)
                }
                is HomeState.Loading -> LoadingDialog(stringResource(R.string.home_loading))
            }
        }
    }
}
