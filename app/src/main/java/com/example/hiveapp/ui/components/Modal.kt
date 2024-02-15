package com.example.hiveapp.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun Modal(
    dialogTitle: String,
    dialogText: String,
    icon: ImageVector,
    isModalActive: Boolean,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
) {
    if (isModalActive) {
        AlertDialog(
            icon = {
                Icon(icon, contentDescription = "Example Icon")
            },
            title = {
                Text(text = dialogTitle)
            },
            text = {
                Text(text = dialogText)
            },
            onDismissRequest = {
                onDismissRequest()
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onConfirmation()
                    }
                ) {
                    Text("Usuń")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        onDismissRequest()
                    }
                ) {
                    Text("Anuluj")
                }
            }
        )
    }
}
