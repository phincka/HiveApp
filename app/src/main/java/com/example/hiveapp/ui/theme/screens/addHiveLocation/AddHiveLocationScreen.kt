package com.example.hiveapp.ui.theme.screens.addHiveLocation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import com.example.hiveapp.R
import com.example.hiveapp.ui.components.LoadingDialog
import com.example.hiveapp.ui.components.TextError
import com.example.hiveapp.ui.components.TopBar
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.Circle
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.result.ResultBackNavigator
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun AddHiveLocation(
    id: String,
    lat: Double,
    lng: Double,
    resultNavigator: ResultBackNavigator<Boolean>
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val addHiveLocationViewModel: AddHiveLocationViewModel = koinViewModel()
    val addHiveLocationState by addHiveLocationViewModel.addHiveLocationState.collectAsState()

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(lat, lng), 13f)
    }

    val uiSettings = remember { MapUiSettings(zoomControlsEnabled = false) }

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopBar(
                backNavigation = { resultNavigator.navigateBack(result = true) },
                scrollBehavior,
                title = stringResource(R.string.add_hive_location_title),
                content = { }
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (addHiveLocationState) {
                is AddHiveLocationState.Success -> {
                    val locations = (addHiveLocationState as AddHiveLocationState.Success).locations

                    GoogleMap(
                        modifier = Modifier.fillMaxSize(),
                        properties = addHiveLocationViewModel.mapState.properties,
                        cameraPositionState = cameraPositionState,
                        uiSettings = uiSettings,
                        onMapLongClick = { it ->
                            if (locations.any { it.id == id }) {
                                addHiveLocationViewModel.updateHiveLocation(id, it.latitude, it.longitude)

                                scope.launch {
                                    snackbarHostState.showSnackbar(
                                        message = "Zmieniono położenie ula",
                                        actionLabel = "Zamknij",
                                        duration = SnackbarDuration.Short
                                    )
                                }
                            } else {
                                addHiveLocationViewModel.updateHiveLocation(id, it.latitude, it.longitude)

                                scope.launch {
                                    snackbarHostState.showSnackbar(
                                        message = "Dodano nową lokalizację ula",
                                        actionLabel = "Zamknij",
                                        duration = SnackbarDuration.Short
                                    )
                                }
                            }
                        }
                    ) {
                        locations.forEach { spot ->
                            Marker(
                                state = MarkerState(LatLng(spot.lat, spot.lng)),
                                title = "${stringResource(R.string.add_hive_location_marker_title)} ${spot.name}",
                                snippet = stringResource(R.string.add_hive_location_marker_snippet),
                                icon = BitmapDescriptorFactory.defaultMarker(
                                    BitmapDescriptorFactory.HUE_RED
                                )
                            )

                            Circle(
                                center = LatLng(spot.lat, spot.lng),
                                clickable = true,
                                fillColor = Color.Blue.copy(alpha = 0.3f),
                                radius = 1500.0,
                                strokeColor = Color.Black,
                                strokeWidth = 2f,
                            )
                        }
                    }
                }

                is AddHiveLocationState.Error -> {
                    val errorMessage = (addHiveLocationState as AddHiveLocationState.Error).message
                    TextError(errorMessage)
                }

                is AddHiveLocationState.Loading -> LoadingDialog(stringResource(R.string.add_hive_location_loading))
            }
        }
    }
}
