package com.example.workordie.ui.screen


import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.workordie.ui.theme.WorkOrDieTheme


/* Not yet done:
*  1. Top bar
*  2. Add subtasks should be '+' icon
*  3. Formatting
*  4. cant work now,it's only ui
* */
@Composable
fun AddTaskBody(){
    Column(){
        Text(
            text = "New Task",
            textAlign = TextAlign.Center
        )
        TextField(
            value = "機率",
            onValueChange = { },
            label = { Text(text = "Name") }
        )
        TextField(
            value = "作業",
            onValueChange = { },
            label = { Text(text = "Type") }
        )
        TextField(
            value = "5/30",
            onValueChange = { },
            label = { Text(text = "Deadline") }
        )
        TextField(
            value = "5",
            onValueChange = { },
            label = { Text(text = "Estimated Time to Finish(in hour)") }
        )
        TextField(
            value = "5/20",
            onValueChange = { },
            label = { Text(text = "Estimated Start Date") }
        )
        TextField(
            value = "+",
            onValueChange = { },
            label = { Text(text = "Add subtasks") }
        )
        Button(
            onClick = {  }
        ) {
            Text(text = "submit")
        }
    }

}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WorkOrDieTheme {
        AddTaskBody()
    }
}