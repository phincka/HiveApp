package com.example.hiveapp.ui.theme.screens.signIn

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.hiveapp.R
import com.example.hiveapp.data.util.AuthState
import com.example.hiveapp.ui.components.InputField
import com.example.hiveapp.ui.components.LoadingDialog
import com.example.hiveapp.ui.components.PasswordField
import com.example.hiveapp.ui.components.TextButton
import com.example.hiveapp.ui.components.TextError
import com.example.hiveapp.ui.components.TextOutlinedButton
import com.example.hiveapp.ui.theme.Typography
import com.example.hiveapp.ui.theme.screens.destinations.HomeScreenDestination
import com.example.hiveapp.ui.theme.screens.destinations.SignUpScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
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
        Text(
            text = stringResource(R.string.signIn_title),
            style = Typography.headlineLarge,
            color = Color.Black,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            InputField(
                label = stringResource(R.string.signIn_form_login),
                value = email,
                setValue = { newValue ->
                    email = newValue
                },
                icon = Icons.Default.Email,
            )

            PasswordField(
                label = stringResource(R.string.signIn_form_password),
                value = password,
                setValue = { newValue ->
                    password = newValue
                },
                icon = Icons.Default.Lock
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(
            text = stringResource(R.string.signIn_login_button),
            modifier = Modifier.fillMaxWidth(),
            onClick = { signInViewModel.signIn(email, password) },
        )

        TextOutlinedButton(
            text = stringResource(R.string.signIn_register_button),
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                navigator.navigate(SignUpScreenDestination)
            },
        )

        Row(
            modifier = Modifier.padding(top = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(stringResource(R.string.signIn_reset_password_text))
            Text(
                text = stringResource(R.string.signIn_reset_password_button),
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.clickable {
                    // TODO: DODAĆ WIDOK
                    Log.d("APP_LOG", "TODO RESETUJ HASŁO")
                }
            )
        }

        when(signInState.value) {
            is AuthState.Loading -> LoadingDialog(stringResource(R.string.signIn_title))

            is AuthState.Success -> {
                val success = (signInState.value as AuthState.Success).success
                if (success) navigator.navigate(HomeScreenDestination)
            }

            is AuthState.Error ->  {
                val errorMessage = (signInState.value as AuthState.Error).error
                TextError(errorMessage)
            }
        }
    }
}