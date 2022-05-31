package com.example.workordie.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.workordie.ui.theme.WorkOrDieTheme

/* TODO
the background color of subtask 1 to 3
* */

@Composable
fun FinishSubmitBody(){
    Column(
        modifier = Modifier.padding(30.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "You've added a new task")

        Text(
            text = "<HW name>",
            fontSize = 25.sp
        )

        Text(
            text = "with subtasks below:"
        )

        for (i in 1..3){
            Text(
                text = "subtask $i"
            )
        }

        //add some space between subtasks and button
        Spacer(modifier = Modifier.height(60.dp))

        Button(
            onClick = { /*TODO*/ }
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
fun FinishSubmitPreview() {
    WorkOrDieTheme {
        FinishSubmitBody()
    }
}