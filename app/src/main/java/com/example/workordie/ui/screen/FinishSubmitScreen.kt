package com.example.workordie.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.workordie.ui.theme.Blue900
import com.example.workordie.ui.theme.WorkOrDieTheme
import com.example.workordie.ui.theme.Yellow100

/* TODO
Background color
onclick button
Font, font size
* */
//@Preview
@Composable
fun FinishSubmit(navController: NavController){
    Column(
        modifier = Modifier.padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        Text(
            text = "You've added a new task",
            fontSize = 20.sp
        )
        Text(
            text = "<HW name>",
            fontSize = 22.sp,
            fontWeight = FontWeight.Medium
        )
        Text(
            text = "with subtasks below:",
            fontSize = 20.sp
        )
            /*WorkOrDieTheme{
                Subtasks()
            }*/
            Subtasks()
        //add some space between subtasks and button
        Spacer(modifier = Modifier.height(60.dp))

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

//@Preview(showBackground = true, backgroundColor = 0xFFFEFBEF)
@Composable
private fun Subtasks(names: List<String> = List(3) { "$it" }) {
    LazyColumn {
        items(items = names) {name ->
            Subtask(name = name)
        }
    }
}

@Composable
private fun Subtask(name: String) {
    Card(
        backgroundColor = Yellow100,
        modifier = Modifier
            .padding(vertical = 1.dp, horizontal = 1.dp)
            .size(width = 200.dp, height = 25.dp)
    ) {
        CardContent(name)
    }
}

@Composable
private fun CardContent(name: String) {
    Column(
        //modifier = Modifier.padding(6.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(
            text = "Subtask ",
            color = Blue900
        )
        /*Text(text = name,
             color = Blue900)*/
    }
}

@Preview(showBackground = true)
@Composable
fun FinishSubmitPreview() {
    WorkOrDieTheme {
        FinishSubmit(rememberNavController())
    }
}
