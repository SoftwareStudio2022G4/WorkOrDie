package com.example.workordie.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.workordie.ui.theme.WorkOrDieTheme

/* TODO
Background color
Boxes of subtask 1 to 3
onclick button
Font, font size
* */
//@PreviewParameter
@Composable
fun FinishSubmit(navController: NavController){
        Column(
        modifier = Modifier.padding(30.dp),
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
            WorkOrDieTheme{
                Subtasks()
            }
        /*for (i in 1..3){
            Text(
                text = "subtask $i"
            )
        }*/
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

@Preview
@Composable
private fun Subtasks(names: List<String> = List(3) { "$it" }) {
    LazyColumn(modifier = Modifier.padding(vertical = 2.dp)) {
        items(items = names) {name ->
            Subtask(name = name)
        }
    }
}

@Composable
private fun Subtask(name: String) {
    // TODO: implement Card
    Card(
        backgroundColor = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        CardContent(name)
    }
}

@Composable
private fun CardContent(name: String) {

    Row(
        modifier = Modifier
            .padding(12.dp)
            // TODO: colour?
    ) {
        Text(text = "Subtask ")
        Text(
            text = name
        )
    }
}

/*
@Preview
@Composable
fun TopAppBar(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colors.primarySurface,
    contentColor: Color = contentColorFor(backgroundColor),
    elevation: Dp = AppBarDefaults.TopAppBarElevation,
    contentPadding: PaddingValues = AppBarDefaults.ContentPadding,
    content: (@Composable @ExtensionFunctionType RowScope.() -> Unit)?
) {
}
*/

/*
@Preview(showBackground = true)
@Composable
fun FinishSubmitPreview() {
    WorkOrDieTheme {
        FinishSubmit()
    }
}*/
