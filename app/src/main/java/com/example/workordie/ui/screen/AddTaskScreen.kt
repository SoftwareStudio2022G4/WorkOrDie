package com.example.workordie.ui.screen


import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


/* Not yet done:
*  Top bar
*  cant input now,it's only ui
* */
@Composable
fun AddTask(navController: NavController){
    Column(
        modifier = Modifier.padding(70.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        Text(
            text = "New Task",
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
            fontSize = 36.sp
        )
        OutlinedTextField(
            value = "",
            onValueChange = { },
            label = { Text(text = "Name") },
            placeholder = { Text(text = "機率") }
        )
        OutlinedTextField(
            value = "",
            onValueChange = { },
            label = { Text(text = "Type") },
            placeholder = { Text(text = "作業") }
        )
        OutlinedTextField(
            value = "",
            onValueChange = { },
            label = { Text(text = "Deadline") },
            placeholder = { Text(text = "5/30") }
        )
        OutlinedTextField(
            value = "",
            onValueChange = { },
            label = { Text(text = "Estimated Time to Finish(in hour)") },
            placeholder = { Text(text = "5") }
        )
        OutlinedTextField(
            value = "",
            onValueChange = { },
            label = { Text(text = "Estimated Start Date") },
            placeholder = { Text(text = "5/20") }
        )
        OutlinedTextField(
            value = "+",
            onValueChange = { },
            label = { Text(text = "Add subtasks") }
        )
        Button(
            onClick = { navController.navigate(NavScreen.FinishSubmit.route) },
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
        ) {
            Text(text = "submit")
        }
    }

}


/*@Preview(showBackground = true)
@Composable
fun AddTaskPreview() {
    WorkOrDieTheme {
        AddTask()
    }
}*/