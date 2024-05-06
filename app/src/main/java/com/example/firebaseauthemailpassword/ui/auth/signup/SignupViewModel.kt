package com.example.firebaseauthemailpassword.ui.auth.signup

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor() : ViewModel() {

    val _signupUiState = MutableStateFlow(SignupScreenUiState())
    val signupUiState: StateFlow<SignupScreenUiState> = _signupUiState.asStateFlow()

    fun updateSignupState(signupDetails: SignupDetails) {
        _signupUiState.value = SignupScreenUiState(
            signupDetails = signupDetails,
            isValidData = validateInputs(signupDetails)
        )
    }

    private fun validateInputs(signupDetails: SignupDetails): Boolean {
        return signupDetails.userName.isNotBlank() && signupDetails.email.isNotBlank() && signupDetails.password.isNotBlank()
    }

}

data class SignupScreenUiState(
    val signupDetails: SignupDetails = SignupDetails(),
    val isValidData: Boolean = false
)

data class SignupDetails(
    val userName: String = "",
    val email: String = "",
    val password: String = ""
)