package com.example.workordie.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.workordie.ui.theme.Yellow100

/* TODO
need a better format
* */

@Composable
fun FinishSubmit(navController: NavController){
    Column(
        modifier = Modifier.padding(120.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "You've added a new task",
            fontSize = 28.sp
        )

        Text(
            text = "<HW name>",
            fontSize = 36.sp
        )

        Text(
            text = "with subtasks below:"
        )

        for (i in 1..3){
            Surface(
                color = Yellow100
            ) {
                Text(
                    text = "subtask $i"
                )
            }
        }

        //add some space between subtasks and button
        Spacer(modifier = Modifier.height(60.dp))

        //after pressing Done button,we should pop all screens including Home screen in the backstack
        //then we can navigate to Home screen
        Button(
            onClick = {
                navController.navigate(NavScreen.Home.route){
                    popUpTo(NavScreen.Home.route){
                        inclusive = true
                    }
                }
            }
        ) {
            Text(text = "Done")
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Done"
            )
        }
    }
}

/*@Preview(showBackground = true)
@Composable
fun FinishSubmitPreview() {
    WorkOrDieTheme {
        FinishSubmit()
    }
}*/