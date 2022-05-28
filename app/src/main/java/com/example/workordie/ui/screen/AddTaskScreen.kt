package com.example.workordie.ui.screen


import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.workordie.ui.theme.WorkOrDieTheme


/* Not yet done:
*  Top bar
*  Add subtasks should be '+' icon
*  cant work now,it's only ui
* */
@Composable
fun AddTaskBody(){
    Column(
        modifier = Modifier.padding(30.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        Text(
            text = "New Task",
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
            fontSize = 36.sp
        )
        OutlinedTextField(
            value = "機率",
            onValueChange = { },
            label = { Text(text = "Name") }
        )
        OutlinedTextField(
            value = "作業",
            onValueChange = { },
            label = { Text(text = "Type") }
        )
        OutlinedTextField(
            value = "5/30",
            onValueChange = { },
            label = { Text(text = "Deadline") }
        )
        OutlinedTextField(
            value = "5",
            onValueChange = { },
            label = { Text(text = "Estimated Time to Finish(in hour)") }
        )
        OutlinedTextField(
            value = "5/20",
            onValueChange = { },
            label = { Text(text = "Estimated Start Date") }
        )
        OutlinedTextField(
            value = "+",
            onValueChange = { },
            label = { Text(text = "Add subtasks") }
        )
        Button(
            onClick = {  },
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
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