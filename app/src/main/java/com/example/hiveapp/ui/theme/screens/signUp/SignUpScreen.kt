package com.example.hiveapp.ui.theme.screens.signUp

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
import com.example.hiveapp.ui.theme.Typography
import com.example.hiveapp.ui.theme.screens.destinations.SignInScreenDestination
import com.example.hiveapp.ui.theme.screens.destinations.VerifyEmailScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@Destination
@Composable
fun SignUpScreen(
    navigator: DestinationsNavigator,
    signUpViewModel: SignUpViewModel = koinViewModel()
) {
    val signUpState = signUpViewModel.signUpState.collectAsState()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var repeatPassword by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {


        Text(
            text = stringResource(R.string.signUp_title),
            style = Typography.headlineLarge,
            color = Color.Black,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            InputField(
                label = stringResource(R.string.signUp_form_login),
                value = email,
                setValue = { newValue ->
                    email = newValue
                },
                icon = Icons.Default.Email,
            )
            PasswordField(
                label = stringResource(R.string.signUp_form_password),
                value = password,
                setValue = { newValue ->
                    password = newValue
                },
                icon = Icons.Default.Lock,
            )

            PasswordField(
                label = stringResource(R.string.signUp_form_repeat_password),
                value = repeatPassword,
                setValue = { newValue ->
                    repeatPassword = newValue
                },
                icon = Icons.Default.Lock,
            )

        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(
            text = stringResource(R.string.signUp_register_button),
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                signUpViewModel.signUp(email, password, repeatPassword)
            },
        )

        Row(
            modifier = Modifier.padding(top = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(stringResource(R.string.signUp_reset_password_text))
            Text(
                text = stringResource(R.string.signUp_reset_password_button),
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.clickable {
                    navigator.navigate(SignInScreenDestination)
                }
            )
        }

        when(signUpState.value) {
            is AuthState.Loading -> LoadingDialog(stringResource(R.string.signUp_loading))

            is AuthState.Success -> {
                val success = (signUpState.value as AuthState.Success).success
                if (success) navigator.navigate(VerifyEmailScreenDestination)
            }

            is AuthState.Error ->  {
                val errorMessage = (signUpState.value as AuthState.Error).error
                TextError(errorMessage)
            }
        }
    }
}



