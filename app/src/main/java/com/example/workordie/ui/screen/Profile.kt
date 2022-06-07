package com.example.workordie.ui.screen

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
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
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.outlined.Email
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.workordie.R



@Composable
fun profile(modifier : Modifier = Modifier){
    androidx.compose.material.Surface(color = MaterialTheme.colors.background)
    {

        Column() {

            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = {}) {
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
            TextField(
                value = "Log in to your email",
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp)


            )
            TextField(
                value = "distraction type",
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp)


            )

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
        profile()
    }
}