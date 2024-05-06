package com.example.firebaseauthemailpassword.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SubmitButton(
    onSubmit: () -> Unit,
    enabled: Boolean,
    label: @Composable () -> Unit,
) {
    Button(
        onClick = onSubmit,
        modifier = Modifier.fillMaxWidth(),
        enabled = enabled
    ) {
        label()
    }
}