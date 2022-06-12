package com.example.workordie.ui.screen

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.workordie.ui.GoogleSignIn.GoogleSignInButton
import com.example.workordie.ui.GoogleSignIn.SignInViewModel
import com.example.workordie.ui.GoogleSignIn.User
import com.example.workordie.ui.GoogleSignIn.utils.AuthResult
import com.google.android.gms.common.api.ApiException
import kotlinx.coroutines.launch

@Composable
fun SignInScreen(signInViewModel: SignInViewModel) {
    val scope = rememberCoroutineScope()
    var text by remember { mutableStateOf<String?>(null) }
    val user by remember(signInViewModel) { signInViewModel.user }.collectAsState()
    val signInRequestCode = 1

    val authResultLauncher = rememberLauncherForActivityResult(contract = AuthResult()) { task ->
        try {
            val account = task?.getResult(ApiException::class.java)
            if (account == null) {
                text = "Google Sign In Failed"
            } else {
                scope.launch {
                    signInViewModel.setSignInValue(
                        email = account.email!!,
                        displayName = account.displayName!!
                    )
                }
            }
        } catch (e: ApiException) {
            text = e.localizedMessage
        }
    }

    AuthView(
        errorText = text,
        onClick = {
            text = null
            authResultLauncher.launch(signInRequestCode)
        }
    )

    user?.let {
        GoogleSignInScreen(it)
    }
}

@Composable
fun AuthView(
    errorText: String?,
    onClick: () -> Unit
) {
    var isLoading by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Google Sign In",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GoogleSignInButton(
                text = "Sign In with Google",
                loadingText = "Signing In...",
                isLoading = isLoading,
                onClick = {
                    isLoading = true
                    onClick()
                }
            )

            errorText?.let {
                isLoading = false

                Spacer(modifier = Modifier.height(30.dp))

                Text(
                    text = it
                )
            }
        }
    }
}

@Composable
fun GoogleSignInScreen(
    user: User
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Sign In Successful",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Welcome! ${user.displayName}",
                fontSize = 30.sp,
                style = MaterialTheme.typography.h4,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = user.email,
                color = Color.Gray,
                fontSize = 20.sp
            )
        }
    }
}