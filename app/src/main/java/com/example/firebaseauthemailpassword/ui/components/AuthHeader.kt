package com.example.firebaseauthemailpassword.ui.components

import androidx.compose.foundation.Image
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.example.firebaseauthemailpassword.R

@Composable
fun AuthHeader(title: String) {
    Image(
        painter = painterResource(id = R.drawable.firebase),
        contentDescription = "firebase logo"
    )
    Text(
        text = "Firebase $title",
        style = MaterialTheme.typography.headlineMedium
    )
}