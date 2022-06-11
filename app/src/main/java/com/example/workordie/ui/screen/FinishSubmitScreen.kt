package com.example.workordie.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.workordie.ui.theme.Blue900
import com.example.workordie.ui.theme.WorkOrDieTheme
import com.example.workordie.ui.theme.Yellow100
import kotlinx.coroutines.CoroutineScope

/*TODO:
shadow of button
the font of button is different from the others
*/

@Composable
fun FinishSubmit(navController: NavController){
    val scaffoldState : ScaffoldState = rememberScaffoldState(/*rememberDrawerState(DrawerValue.Closed)*/)
    Scaffold(scaffoldState = scaffoldState,) {

        Column() {
            FinishSubmitBody(navController = navController)
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

}
@Composable
fun FinishSubmitBody(navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 220.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "You've added a new task",
            fontWeight = FontWeight.Bold,
            //color = MaterialTheme.colors.onSurface,
            fontSize = 30.sp
        )
        Text(
            text = "CA HW6 !",
            fontWeight = FontWeight.Bold,
            //color = MaterialTheme.colors.onSurface,
            fontSize = 30.sp
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "with subtasks below:",
            //color = MaterialTheme.colors.onSurface,
            fontSize = 26.sp
        )

        Spacer(modifier = Modifier.height(10.dp))
        Subtasks()
        //add some space between subtasks and button
        Spacer(modifier = Modifier.height(140.dp))

        Button(
            onClick = {
                navController.navigate(NavScreen.Home.route){
                    popUpTo(NavScreen.Home.route){
                        inclusive = true
                    }
                }
            },
            modifier = Modifier.size(width = 180.dp, height = 40.dp),
            content = {
                Text(text = "Done")
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Done"
                )
            },
            shape = RoundedCornerShape(6.dp)
        )
    }
}

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
        //backgroundColor = Yellow100,
        shape = RoundedCornerShape(2.dp),
        modifier = Modifier
            .padding(vertical = 1.dp, horizontal = 1.dp)
            .size(width = 340.dp, height = 40.dp)
    ) {
        CardContent(name)
    }
}

@Composable
private fun CardContent(name: String) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Subtask ",
            //color = Blue900,
            fontSize = 22.sp
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFEFBEF)
@Composable
fun FinishSubmitPreview() {
    WorkOrDieTheme {
        FinishSubmit(rememberNavController())
    }
}
