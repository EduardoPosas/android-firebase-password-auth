package com.example.firebaseauthemailpassword.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.firebaseauthemailpassword.ui.auth.AuthViewModel
import com.example.firebaseauthemailpassword.ui.auth.login.LoginScreen
import com.example.firebaseauthemailpassword.ui.auth.signup.SignupScreen
import com.example.firebaseauthemailpassword.ui.profile.ProfileScreen

@Composable
fun AppNavHost() {
    val navController: NavHostController = rememberNavController()
    val authViewModel: AuthViewModel = hiltViewModel()
    NavHost(
        navController = navController,
        startDestination = ROUTE_LOGIN
    ) {
        composable(route = ROUTE_LOGIN) {
            LoginScreen(
                navController = navController,
                authViewModel = authViewModel
            )
        }

        composable(route = ROUTE_SIGNUP) {
            SignupScreen(
                navController = navController,
                authViewModel = authViewModel
            )
        }

        composable(route = ROUTE_HOME) {
            ProfileScreen(
                navController = navController,
                authViewModel = authViewModel
            )
        }
    }
}