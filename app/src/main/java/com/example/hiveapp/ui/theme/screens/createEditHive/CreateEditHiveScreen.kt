package com.example.hiveapp.ui.theme.screens.createEditHive

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.hiveapp.R
import com.example.hiveapp.data.model.HiveModel
import com.example.hiveapp.notifications.NotificationService
import com.example.hiveapp.ui.components.ExposedDropdown
import com.example.hiveapp.ui.components.TextButton
import com.example.hiveapp.ui.components.TopBar
import com.example.hiveapp.ui.theme.Typography
import org.koin.androidx.compose.get
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateEditHiveScreen(
    navController: NavController,
    moveRoute: String
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val createEditHiveViewModel: CreateEditHiveViewModel = koinViewModel()
    val notificationService = get<NotificationService>()

    var isDropdownMenuVisible by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopBar(
                navController,
                scrollBehavior,
                title = stringResource(R.string.create_hive_title),
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
    ) { innerPadding ->
        val topPadding = innerPadding.calculateTopPadding() + 12.dp
        val bottomPadding = 24.dp
        val horizontalPadding = 24.dp

        val currentTimestamp = System.currentTimeMillis()
        var hiveData: HiveModel by remember {
            mutableStateOf(
                HiveModel(
                    0,
                    0,
                    "",
                    0,
                    0,
                    0,
                    "",
                    0,
                    0,
                    "",
                    0.0,
                    0.0,
                    currentTimestamp,
                    currentTimestamp
                )
            )
        }

        var familyType by remember { mutableIntStateOf(hiveData.familyType) }
        var type by remember { mutableIntStateOf(hiveData.type) }
        var breed by remember { mutableIntStateOf(hiveData.breed) }
        var year by remember { mutableIntStateOf(hiveData.year) }
        var state by remember { mutableIntStateOf(hiveData.state) }

        var familyTypeExpanded by remember { mutableStateOf(false) }
        var typeExpanded by remember { mutableStateOf(false) }
        var breedExpanded by remember { mutableStateOf(false) }
        var yearExpanded by remember { mutableStateOf(false) }
        var stateExpanded by remember { mutableStateOf(false) }

        Column(
            modifier = Modifier
                .padding(
                    start = horizontalPadding,
                    end = horizontalPadding,
                    top = topPadding,
                    bottom = bottomPadding
                )
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.create_hive_form_hive_title),
                style = Typography.titleMedium,
            )

            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = hiveData.name,
                onValueChange = { newValue ->
                    hiveData = hiveData.copy(name = newValue)
                },
                label = {
                    Text(stringResource(R.string.create_hive_form_name))
                },
                singleLine = true,
                keyboardActions = KeyboardActions(
                    onDone = {}
                ),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                )
            )

            ExposedDropdown(
                expanded = familyTypeExpanded,
                setExpanded = { familyTypeExpanded = it },
                options = DataConstants.familyType,
                setSelected = { familyType = it },
                selected = hiveData.familyType,
                label = stringResource(R.string.create_hive_form_family_type)
            )

            ExposedDropdown(
                expanded = typeExpanded,
                setExpanded = { typeExpanded = it },
                options = DataConstants.hiveType,
                selected = type,
                setSelected = { type = it },
                label = stringResource(R.string.create_hive_form_type)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.create_hive_form_queen_title),
                style = Typography.titleMedium,
            )

            ExposedDropdown(
                expanded = breedExpanded,
                setExpanded = { breedExpanded = it },
                options = DataConstants.queenBreed,
                selected = breed,
                setSelected = { breed = it },
                label = stringResource(R.string.create_hive_form_queen_breed)
            )

            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = hiveData.line,
                onValueChange = { newValue ->
                    hiveData = hiveData.copy(line = newValue)
                },
                label = { stringResource(R.string.create_hive_form_queen_line) },
                singleLine = true,
                keyboardActions = KeyboardActions(
                    onDone = {}
                ),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                )
            )

            ExposedDropdown(
                expanded = yearExpanded,
                setExpanded = { yearExpanded = it },
                options = DataConstants.queenYear,
                selected = year,
                setSelected = { year = it },
                label = stringResource(R.string.create_hive_form_queen_year)
            )

            ExposedDropdown(
                expanded = stateExpanded,
                setExpanded = { stateExpanded = it },
                options = DataConstants.queenState,
                selected = state,
                setSelected = { state = it },
                label = stringResource(R.string.create_hive_form_queen_state)
            )

            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = hiveData.note,
                onValueChange = { newValue ->
                    hiveData = hiveData.copy(note = newValue)
                },
                label = { Text(stringResource(R.string.create_hive_form_queen_note)) },
                minLines = 5,
                keyboardActions = KeyboardActions(
                    onDone = {}
                ),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            TextButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.create_hive_form_button),
                onClick = {
                    handleCreateEditHive(
                        createEditHiveViewModel,
                        hiveData,
                        type,
                        breed,
                        year,
                        state,
                        notificationService,
                        navController,
                        moveRoute
                    )
                }
            )
        }
    }
}

fun handleCreateEditHive(
    viewModel: CreateEditHiveViewModel,
    hiveData: HiveModel,
    type: Int,
    breed: Int,
    year: Int,
    state: Int,
    notificationService: NotificationService,
    navController: NavController,
    moveRoute: String
) {
    viewModel.insertHive(
        HiveModel(
            hiveData.id,
            hiveData.uId,
            hiveData.name,
            type,
            hiveData.familyType,
            breed,
            hiveData.line,
            year,
            state,
            hiveData.note,
            hiveData.lat,
            hiveData.lng,
            hiveData.created,
            hiveData.edited
        )
    )

    notificationService.showCreateNotification()
    navController.navigate(moveRoute)
}

object DataConstants {
    val queenState = listOf(
        "Unasienniona",
        "Nieunasienniona"
    )

    val queenBreed = listOf(
        "Krainka",
        "Buckfast",
        "Włoszka"
    )

    val queenYear = listOf(
        "Biały",
        "Żółty",
        "Czerwony",
        "Zielony",
        "Niebieski"
    )

    val familyType = listOf(
        "Produkcyjna",
        "Odkład",
        "Wychowująca"
    )

    val hiveType = listOf(
        "Wielkopolski",
        "Dadant",
        "Warszawski"
    )
}