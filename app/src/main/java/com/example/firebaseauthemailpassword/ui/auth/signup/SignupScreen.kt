package com.example.firebaseauthemailpassword.ui.auth.signup

import android.content.res.Configuration
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
import androidx.compose.material.icons.filled.Person
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
import androidx.lifecycle.Lifecycle
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
import com.example.firebaseauthemailpassword.ui.components.FormTextInput
import com.example.firebaseauthemailpassword.ui.components.PasswordInput
import com.example.firebaseauthemailpassword.ui.components.SubmitButton
import com.example.firebaseauthemailpassword.ui.theme.FirebaseAuthEmailPasswordTheme

@Composable
fun SignupScreen(
    navController: NavHostController,
    authViewModel: AuthViewModel?
) {

    val signupViewModel: SignupViewModel = hiltViewModel()
    val signupUiState by signupViewModel.signupUiState.collectAsStateWithLifecycle()
    val signupFlow = authViewModel?.signupFlow?.collectAsStateWithLifecycle(minActiveState = Lifecycle.State.STARTED)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AuthHeader(title = "Signup")
        Spacer(modifier = Modifier.height(48.dp))

        SignupForm(
            signupUiState = signupUiState,
            updateSignupState = signupViewModel::updateSignupState,
            onSubmit = {
                authViewModel?.signup(
                    signupUiState.signupDetails.userName,
                    signupUiState.signupDetails.email,
                    signupUiState.signupDetails.password
                )
            }
        )
        Spacer(modifier = Modifier.height(16.dp))

        SignupFooter(navController = navController)

        signupFlow?.value?.let {
            when (it) {
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
                            popUpTo(ROUTE_SIGNUP) { inclusive = true }
                        }
                    }
                }
            }
        }
    }

}

@Composable
fun SignupForm(
    signupUiState: SignupScreenUiState,
    updateSignupState: (SignupDetails) -> Unit,
    onSubmit: () -> Unit
) {
    FormTextInput(
        value = signupUiState.signupDetails.userName,
        onValueChange = { updateSignupState(signupUiState.signupDetails.copy(userName = it)) },
        label = { Text(text = "Username") },
        icon = Icons.Default.Person
    )
    Spacer(modifier = Modifier.height(8.dp))

    EmailInput(
        value = signupUiState.signupDetails.email,
        onValueChange = { updateSignupState(signupUiState.signupDetails.copy(email = it)) },
        label = { Text(text = "Email") },
        icon = Icons.Default.Email
    )
    Spacer(modifier = Modifier.height(8.dp))

    PasswordInput(
        value = signupUiState.signupDetails.password,
        onValueChange = { updateSignupState(signupUiState.signupDetails.copy(password = it)) },
        label = { Text(text = "Password") },
        icon = Icons.Default.Password
    )
    Spacer(modifier = Modifier.height(32.dp))

    SubmitButton(
        onSubmit = onSubmit,
        enabled = signupUiState.isValidData
    ) {
        Text(text = "Signup")
    }
}

@Composable
fun SignupFooter(navController: NavHostController) {
    Text(
        text = "Already have an account?",
        style = MaterialTheme.typography.bodySmall
    )
    FormTextButton(onTextClick = {
        navController.navigate(ROUTE_LOGIN) {
            popUpTo(ROUTE_SIGNUP) { inclusive = true }
        }
    }) {
        Text(
            text = "Click here to login",
            style = MaterialTheme.typography.bodySmall
        )
    }
}


@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun SignupScreenPreviewLight() {
    FirebaseAuthEmailPasswordTheme {
        SignupScreen(navController = rememberNavController(), null)
    }
}