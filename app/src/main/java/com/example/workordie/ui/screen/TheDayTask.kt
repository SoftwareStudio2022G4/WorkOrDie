package com.example.workordie.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.workordie.ui.theme.WorkOrDieTheme

@Composable
fun DailyTask(){
    val scaffoldState : ScaffoldState = rememberScaffoldState(/*rememberDrawerState(DrawerValue.Closed)*/)
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(

                title = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Filled.ArrowBack, "backIcon")
                    }

                    Spacer(modifier = Modifier.width(240.dp))
                    Text("6/7's task")
                },

            )
        },
        content = { DailyTaskContent() }
    )


}
@Composable
fun DailyTaskContent(){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {



        Text(
            text = "Tasks:",
            fontSize = 30.sp,
            textAlign = TextAlign.Right
        )
        for(i in 1..3){
            Row(
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(text = "Task $i")
                Text(text = "$i h")
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        Icons.Default.Menu,
                        contentDescription = "Menu"
                    )
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        Icons.Default.PlayArrow,
                        contentDescription = "PlayButton"
                    )
                }
            }
        }


    }
}
@Preview(showBackground = true)
@Composable
fun DailyTaskPreview() {
    WorkOrDieTheme {
//        dailyTask()
    }
}