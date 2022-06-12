package com.example.workordie.ui.screen

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.ContentAlpha.medium
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.workordie.ui.theme.WorkOrDieTheme
import androidx.compose.material.Icon

import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.outlined.Email
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.workordie.R
import com.example.workordie.ui.GoogleSignIn.GoogleSignInButton
import com.example.workordie.ui.GoogleSignIn.User


@Composable
fun Profile(modifier : Modifier = Modifier, navController : NavController){
    
    Scaffold() {
        ProfileScreen(navController = navController)
    }
}
@Composable
fun ProfileScreen(modifier : Modifier = Modifier, navController : NavController){
    androidx.compose.material.Surface(color = MaterialTheme.colors.background)
    {

        Column() {

            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = {navController.navigate(NavScreen.Home.route)}) {
                        Icon(Icons.Filled.ArrowBack, "backIcon")

                    }
                },


                )
            Card(modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
            ) {

                Text(
                    text = "User Name",
                    modifier = modifier
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
                    Spacer(modifier = Modifier.width(200.dp))
                    IconButton(onClick = { navController.navigate(NavScreen.GoogleSignIn.route)}) {
                            Icon(Icons.Filled.ArrowForward, "forwardIcon")

                    }

                }

            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp)
                    .height(50.dp)

            ){
                Text(text = "distraction type")
            }

            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier.padding(horizontal = 150.dp, vertical = 80.dp)

            ) {
                Text(text = "Logout")

            }



        }
    }
}


@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    WorkOrDieTheme {
        Profile(modifier = Modifier,rememberNavController())

    }
}
