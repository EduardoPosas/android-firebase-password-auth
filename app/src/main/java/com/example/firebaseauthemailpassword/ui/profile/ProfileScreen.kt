package com.example.firebaseauthemailpassword.ui.profile

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.firebaseauthemailpassword.R
import com.example.firebaseauthemailpassword.navigation.ROUTE_HOME
import com.example.firebaseauthemailpassword.navigation.ROUTE_LOGIN
import com.example.firebaseauthemailpassword.ui.auth.AuthViewModel
import com.example.firebaseauthemailpassword.ui.theme.FirebaseAuthEmailPasswordTheme

@Composable
fun ProfileScreen(
    navController: NavHostController,
    authViewModel: AuthViewModel?
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        ProfileHeader(
            authViewModel = authViewModel
        )

        Spacer(modifier = Modifier.height(48.dp))

        Text(
            text = "Profile",
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus eros sem, fringilla ac mi at, sagittis pretium ipsum. Nam laoreet et eros in viverra. Suspendisse volutpat mattis orci eget elementum. Aenean dapibus in felis et condimentum. Sed finibus pharetra metus ut dapibus. Maecenas facilisis mauris id ultrices luctus. Aliquam viverra et arcu a ullamcorper.",
            style = MaterialTheme.typography.bodySmall
        )
        Spacer(modifier = Modifier.height(32.dp))

        LogoutButton(
            authViewModel = authViewModel,
            navController = navController,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}


@Composable
fun ProfileHeader(
    authViewModel: AuthViewModel?,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.avatar),
                contentDescription = null,
                modifier = Modifier
                    .size(48.dp),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Welcome Back",
                    style = MaterialTheme.typography.labelMedium
                )
                Text(
                    text = authViewModel?.currentUser?.email ?: "",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Black
                )
            }
        }
    }
}

@Composable
fun LogoutButton(
    authViewModel: AuthViewModel?,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = {
            authViewModel?.logout()
            navController.navigate(ROUTE_LOGIN) {
                popUpTo(ROUTE_HOME) {
                    inclusive = true
                }
            }
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Red
        ),
        modifier = modifier
    ) {
        Text(text = "Logout")
    }
}


@Preview(
    showSystemUi = true,
    showBackground = true,
    uiMode = UI_MODE_NIGHT_NO
)
@Composable
fun ProfileScreenLightPreview() {
    FirebaseAuthEmailPasswordTheme {
        ProfileScreen(navController = rememberNavController(), null)
    }
}