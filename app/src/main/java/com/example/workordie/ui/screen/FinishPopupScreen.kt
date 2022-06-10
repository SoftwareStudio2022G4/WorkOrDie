package com.example.workordie.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.workordie.ui.theme.WorkOrDieTheme
import kotlinx.coroutines.CoroutineScope

/* TODO
the info box between subtasks and done button
* */

@Composable
fun FinishPopup(navController : NavController){
    val scaffoldState : ScaffoldState = rememberScaffoldState(/*rememberDrawerState(DrawerValue.Closed)*/)

    Scaffold(scaffoldState = scaffoldState) {

        Column() {
            FinishPopupBody(navController = navController)

        }

    }
}
@Composable
fun FinishPopupBody(navController : NavController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 100.dp),
        verticalArrangement = Arrangement.spacedBy(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Hooray!",
            fontSize = 30.sp
        )

        Text(
            text = "You've finished"
        )

        Text(
            text = "2 subtasks!"
        )

        for (i in 1..4){
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                //only check the first two
                //IMPORTANT:this is only for ui purpose
                Checkbox(
                    checked = (i<=2),
                    onCheckedChange = null
                )
                Text(
                    text = "subtask $i"
                )
            }
        }

        //TODO: info box here

        //will handle onclick event after finish designing the counting time screen
        Button(
            onClick = { navController.navigate(NavScreen.Home.route) }
        ) {
            Text(text = "Done")
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Done"
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun FinishPopupPreview() {
    WorkOrDieTheme {
        FinishPopup(navController = rememberNavController())
    }
}