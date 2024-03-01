package com.example.hiveapp.ui.theme.screens.signIn

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.hiveapp.data.util.AuthState
import com.example.hiveapp.ui.components.InputField
import com.example.hiveapp.ui.components.TextButton
import com.example.hiveapp.ui.theme.Typography
import com.example.hiveapp.ui.theme.screens.destinations.HomeScreenDestination
import com.google.firebase.auth.FirebaseAuth
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@Destination
@Composable
fun SignInScreen(
    navigator: DestinationsNavigator,
    signInViewModel: SignInViewModel = koinViewModel()
) {
    val signInState = signInViewModel.signInState.collectAsState()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        when(signInState.value) {
            is AuthState.Loading -> Text("Logowanie...")

            is AuthState.Success -> {
                val success = (signInState.value as AuthState.Success).success
                if (success) navigator.navigate(HomeScreenDestination)
            }

            is AuthState.Error ->  {
                val errorMessage = (signInState.value as AuthState.Error).error
                Text(errorMessage.toString())
            }
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Logowanie",
                style = Typography.headlineLarge,
                color = Color.Black,
                textAlign = TextAlign.Center
            )

            InputField(
                label = "Email",
                value = email,
                setValue = { newValue ->
                    email = newValue
                },
                icon = Icons.Default.Email,
            )
            InputField(
                label = "Hasło",
                value = password,
                setValue = { newValue ->
                    password = newValue
                },
                icon = Icons.Default.Lock,
                keyboardType = KeyboardType.Password
            )
            Spacer(modifier = Modifier.height(16.dp))

            TextButton(
                text = "Zaloguj",
                modifier = Modifier.fillMaxWidth(),
                onClick = { signInViewModel.signIn(email, password) },
            )

            TextButton(
                text = "Wyloguj",
                modifier = Modifier.fillMaxWidth(),
                onClick = { FirebaseAuth.getInstance().signOut() },
            )
        }
    }
}

