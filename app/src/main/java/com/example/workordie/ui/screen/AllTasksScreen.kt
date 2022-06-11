package com.example.workordie.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.workordie.ui.CountDownTime.CountdownTimer
import com.example.workordie.ui.theme.WorkOrDieTheme

@Composable
fun AllTasks(navController: NavController){
    val scaffoldState : ScaffoldState = rememberScaffoldState(/*rememberDrawerState(DrawerValue.Closed)*/)
    Scaffold (
        scaffoldState = scaffoldState,
        content = {
            AlltasksBodyContent(navController)
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(NavScreen.AddTask.route) }
            ){
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "fab icon"
                )
            }
        },
        bottomBar = {
            AllTasksBottomBar(navController)
        }
    )
}

@Composable
fun AlltasksBodyContent(navController: NavController){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            text = "In Progress:",
            fontSize = 30.sp,
            textAlign = TextAlign.Right
        )
        for(i in 1..2){
            Row(
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(text = "Task $i")
                IconButton(onClick = { navController.navigate(NavScreen.SubtaskDetail.route) }) {
                    Icon(
                        Icons.Default.Menu,
                        contentDescription = "Menu"
                    )
                }
                IconButton(onClick = { navController.navigate(NavScreen.CountingTime.route) }) {
                    Icon(
                        Icons.Default.PlayArrow,
                        contentDescription = "PlayButton"
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Recommend to Start:",
            fontSize = 30.sp,
            textAlign = TextAlign.Right
        )

        Row(
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(text = "CA Final")
                IconButton(onClick = { navController.navigate(NavScreen.SubtaskDetail.route) }) {
                    Icon(
                        Icons.Default.Menu,
                        contentDescription = "Menu"
                    )
                }
                IconButton(onClick = { navController.navigate(NavScreen.CountingTime.route) }) {
                    Icon(
                        Icons.Default.PlayArrow,
                        contentDescription = "PlayButton"
                    )
                }
        }
        Text(
            text = "Not Started:",
            fontSize = 30.sp,
            textAlign = TextAlign.Right
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(text = "Summer Vacation")
            IconButton(onClick = { navController.navigate(NavScreen.SubtaskDetail.route) }) {
                Icon(
                    Icons.Default.Menu,
                    contentDescription = "Menu"
                )
            }
            IconButton(onClick = { navController.navigate(NavScreen.CountingTime.route) }) {
                Icon(
                    Icons.Default.PlayArrow,
                    contentDescription = "PlayButton"
                )
            }
        }


    }
}

@Composable
fun AllTasksBottomBar(navController: NavController){
    val selectedIndex = remember { mutableStateOf(0) }
    BottomNavigation(
        elevation = 10.dp
    ) {
        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Edit ,
                    contentDescription = "All Tasks"
                )
            },
            label = {
                Text(text = "All Tasks")
            },
            selected = (selectedIndex.value == 0),
            onClick = {
                selectedIndex.value = 0
                navController.navigate(NavScreen.AllTasks.route)
            }
        )

        BottomNavigationItem(
            icon = {
                Icon(imageVector = Icons.Default.CheckCircle,
                    contentDescription = "Today's Task"
                )
            },
            label = {
                Text(text = "Today's Task")
            },
            selected = (selectedIndex.value == 1),
            onClick = {
                selectedIndex.value = 1
                navController.navigate(NavScreen.Home.route)
            }
        )

        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "Calendar"
                )
            },
            label = {
                Text(text = "Calendar")
            },
            selected = (selectedIndex.value == 2),
            onClick = {
                selectedIndex.value = 2
                navController.navigate(NavScreen.Calendar.route)
            }
        )
    }
}
@Preview(showBackground = true)
@Composable
fun AllTasksPreview() {
    WorkOrDieTheme {
        AllTasks(rememberNavController())
    }
}