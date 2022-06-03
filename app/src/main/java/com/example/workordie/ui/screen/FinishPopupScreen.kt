package com.example.workordie.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
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
the info box between subtasks and done button
* */

@Composable
fun FinishPopup(){
    Column(
        modifier = Modifier.padding(30.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
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
fun FinishPopupPreview() {
    WorkOrDieTheme {
        FinishPopup()
    }
}