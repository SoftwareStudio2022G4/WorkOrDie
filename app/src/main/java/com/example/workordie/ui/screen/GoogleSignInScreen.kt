package com.example.workordie.ui.screen

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.workordie.ui.GoogleSignIn.GoogleSignInButton
import com.example.workordie.ui.GoogleSignIn.SignInViewModel
import com.example.workordie.ui.GoogleSignIn.User
import com.example.workordie.ui.GoogleSignIn.utils.AuthResult
import com.google.android.gms.common.api.ApiException
import kotlinx.coroutines.launch

@Composable
fun SignInScreen(signInViewModel: SignInViewModel, navController : NavController) {
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
        },
        navController = navController
    )

    user?.let {
        GoogleSignInScreen(it, navController)
    }
}

@Composable
fun AuthView(
    errorText: String?,
    onClick: () -> Unit,
    navController : NavController
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
                },
                navigationIcon = {
                    IconButton(onClick = {navController.navigate(NavScreen.Home.route)}) {
                        Icon(Icons.Filled.ArrowBack, "backIcon")

                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            Card(modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
            ) {

                Text(
                    text = "User",
                    modifier = Modifier
                        .wrapContentWidth(Alignment.Start)
                        .padding(40.dp)
                )

            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp)

            ){
                Row() {
                    Text(text = "Log in to your email")
                    Spacer(modifier = Modifier.width(100.dp))
                    GoogleSignInButton(
                        text = "Sign In with Google",
                        loadingText = "Signing In...",
                        isLoading = isLoading,
                        onClick = {
                            isLoading = true
                            onClick()
                        }
                    )

                }

            }


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
    user: User,
    navController : NavController
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
                },
                navigationIcon = {
                    IconButton(onClick = {navController.navigate(NavScreen.Home.route)}) {
                        Icon(Icons.Filled.ArrowBack, "backIcon")

                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),


        ) {
            Card(modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
            ) {

                Text(
                    text = "${user.displayName}",
                    modifier = Modifier
                        .wrapContentWidth(Alignment.Start)
                        .padding(40.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = user.email,
                    color = Color.Gray,
                    fontSize = 20.sp
                )
            }
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier.padding(horizontal = 150.dp, vertical = 80.dp)

            ) {
                Text(text = "Logout")

            }






            /*Text(
                text = "Welcome! ${user.displayName}",
                fontSize = 30.sp,
                style = MaterialTheme.typography.h4,
                fontWeight = FontWeight.Bold
            )*/


        }
    }
}