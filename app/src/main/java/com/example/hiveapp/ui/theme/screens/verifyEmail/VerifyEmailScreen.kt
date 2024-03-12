package com.example.hiveapp.ui.theme.screens.verifyEmail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.hiveapp.R
import com.example.hiveapp.data.util.AuthState
import com.example.hiveapp.ui.components.LoadingDialog
import com.example.hiveapp.ui.components.TextButton
import com.example.hiveapp.ui.components.TextError
import com.example.hiveapp.ui.components.TextOutlinedButton
import com.example.hiveapp.ui.theme.Typography
import com.example.hiveapp.ui.theme.screens.destinations.HomeScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@Destination
@Composable
fun VerifyEmailScreen(
    navigator: DestinationsNavigator,
    verifyEmailViewModel: VerifyEmailViewModel = koinViewModel()
) {
    val emailVerificationState = verifyEmailViewModel.verificationEmailState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(R.string.verify_email_title),
            style = Typography.headlineLarge,
            textAlign = TextAlign.Center
        )

        Text(
            text = stringResource(R.string.verify_email_text_1),
            style = Typography.titleMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 32.dp, bottom = 16.dp)
        )

        Text(
            text = stringResource(R.string.verify_email_text_2),
            style = Typography.labelMedium,
            textAlign = TextAlign.Center
        )

        TextButton(
            text = stringResource(R.string.verify_email_button_verify),
            modifier = Modifier.fillMaxWidth().padding(top = 32.dp),
            onClick = {
                verifyEmailViewModel.checkEmailVerification()
            },
        )

        TextOutlinedButton(
            text = stringResource(R.string.verify_email_button_resend),
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                verifyEmailViewModel.resendVerificationEmail()
            },
        )

        Row(
            modifier = Modifier.padding(top = 32.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(stringResource(R.string.verify_email_signout_text))
            Text(
                text = stringResource(R.string.verify_email_signout_button),
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.clickable {
                    verifyEmailViewModel.signOut()
                }
            )
        }

        when (emailVerificationState.value) {
            is AuthState.Loading -> LoadingDialog(stringResource(R.string.home_loading))

            is AuthState.Success -> {
                val success = (emailVerificationState.value as AuthState.Success).success
                val message = (emailVerificationState.value as AuthState.Success).message

                if (success) {
                    navigator.navigate(HomeScreenDestination)
                } else {
                    if (message.isNotEmpty()) Text(text = message, modifier = Modifier.padding(top = 32.dp), style = Typography.titleMedium)
                }
            }

            is AuthState.Error -> {
                val errorMessage = (emailVerificationState.value as AuthState.Error).error
                TextError(errorMessage)
            }
        }
    }
}
