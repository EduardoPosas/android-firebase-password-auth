package com.example.firebaseauthemailpassword.ui.auth.login

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    private val _loginUiState = MutableStateFlow(LoginScreenUiState())
    val loginUiState: StateFlow<LoginScreenUiState> = _loginUiState.asStateFlow()

    fun updateLoginState(loginDetails: LoginDetails) {
        _loginUiState.value = LoginScreenUiState(
            loginDetails = loginDetails,
            isValidData = validateInputs(loginDetails)
        )
    }

    private fun validateInputs(loginDetails: LoginDetails): Boolean {
        return loginDetails.email.isNotBlank() && loginDetails.password.isNotBlank()
    }

}

data class LoginScreenUiState(
    val loginDetails: LoginDetails = LoginDetails(),
    val isValidData: Boolean = false
)

data class LoginDetails(
    val email: String = "",
    val password: String = ""
)