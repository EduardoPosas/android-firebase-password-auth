package com.example.firebaseauthemailpassword.ui.components

import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun FormTextButton(
    onTextClick: () -> Unit,
    label: @Composable () -> Unit
) {
    TextButton(
        onClick = onTextClick
    ) {
        label()
    }
}