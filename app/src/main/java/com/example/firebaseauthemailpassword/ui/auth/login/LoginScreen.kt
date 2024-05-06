package com.example.firebaseauthemailpassword.ui.auth.login

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Password
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.firebaseauthemailpassword.data.Resource
import com.example.firebaseauthemailpassword.navigation.ROUTE_HOME
import com.example.firebaseauthemailpassword.navigation.ROUTE_LOGIN
import com.example.firebaseauthemailpassword.navigation.ROUTE_SIGNUP
import com.example.firebaseauthemailpassword.ui.auth.AuthViewModel
import com.example.firebaseauthemailpassword.ui.components.AuthHeader
import com.example.firebaseauthemailpassword.ui.components.EmailInput
import com.example.firebaseauthemailpassword.ui.components.FormTextButton
import com.example.firebaseauthemailpassword.ui.components.PasswordInput
import com.example.firebaseauthemailpassword.ui.components.SubmitButton
import com.example.firebaseauthemailpassword.ui.theme.FirebaseAuthEmailPasswordTheme

@Composable
fun LoginScreen(
    navController: NavHostController,
    authViewModel: AuthViewModel?
) {

    val loginVieModel: LoginViewModel = hiltViewModel()
    val loginUiState by loginVieModel.loginUiState.collectAsStateWithLifecycle()
    val loginFlow = authViewModel?.loginFlow?.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AuthHeader(title = "Login")

        Spacer(modifier = Modifier.height(48.dp))

        LoginForm(
            loginUiState = loginUiState,
            updateLoginState = loginVieModel::updateLoginState,
            onSubmit = {
                authViewModel?.login(
                    loginUiState.loginDetails.email,
                    loginUiState.loginDetails.password
                )
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        LoginFooter(navController = navController)

        loginFlow?.value?.let {
            when(it) {
                is Resource.Failure -> {
                    val context = LocalContext.current
                    Toast.makeText(
                        context,
                        it.exception.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
                Resource.Loading -> {
                    CircularProgressIndicator()
                }
                is Resource.Success -> {
                    LaunchedEffect(Unit) {
                        navController.navigate(ROUTE_HOME) {
                            popUpTo(ROUTE_LOGIN) { inclusive = true }
                        }
                    }
                }
            }
        }

    }
}


@Composable
fun LoginForm(
    loginUiState: LoginScreenUiState,
    updateLoginState: (LoginDetails) -> Unit,
    onSubmit: () -> Unit
) {
    EmailInput(
        value = loginUiState.loginDetails.email,
        onValueChange = { updateLoginState(loginUiState.loginDetails.copy(email = it)) },
        label = { Text(text = "Email") },
        icon = Icons.Default.Email
    )

    Spacer(modifier = Modifier.height(8.dp))

    PasswordInput(
        value = loginUiState.loginDetails.password,
        onValueChange = { updateLoginState(loginUiState.loginDetails.copy(password = it)) },
        label = { Text(text = "Password") },
        icon = Icons.Default.Password
    )

    Spacer(modifier = Modifier.height(32.dp))

    SubmitButton(
        onSubmit = onSubmit,
        enabled = loginUiState.isValidData
    ) {
        Text(
            text = "Login",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun LoginFooter(navController: NavHostController) {
    Text(
        text = "Do not have an account?",
        style = MaterialTheme.typography.bodySmall
    )
    FormTextButton(onTextClick = {
        navController.navigate(ROUTE_SIGNUP) {
            popUpTo(ROUTE_LOGIN) { inclusive = true }
        }
    }) {
        Text(
            text = "Click here to sign up",
            style = MaterialTheme.typography.bodySmall
        )
    }
}


@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = UI_MODE_NIGHT_NO
)
@Composable
fun LoginScreenPreviewLight() {
    FirebaseAuthEmailPasswordTheme {
        LoginScreen(navController = rememberNavController(), null)
    }
}