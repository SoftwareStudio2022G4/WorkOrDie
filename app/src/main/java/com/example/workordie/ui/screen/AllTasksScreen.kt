package com.example.workordie.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.*
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.workordie.TaskViewModel
import com.example.workordie.model.Task
import androidx.navigation.compose.rememberNavController
import com.example.workordie.ui.CountDownTime.CountdownTimer
import com.example.workordie.ui.theme.WorkOrDieTheme
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

private fun CompareDateAllTasks(task: Task): Boolean {
    //today
    val date = Date()
    val dateformat = SimpleDateFormat("yyyy/MM/dd")
    val dateString = dateformat.format(date)


    try {
        val startDate: Date = dateformat.parse(task.startDate)
        val endDate: Date = dateformat.parse(task.endDate)

        //tomorrow
        val c: Calendar = Calendar.getInstance()
        c.setTime(endDate)
        c.add(Calendar.DATE, 1)
        val theDayAfterEndDate = c.getTime()

        Log.d("ABCD", "startDate = ${task.startDate}")
        Log.d("ABCD", "endDate = ${task.endDate}")
        Log.d("ABCD", "today = ${dateString}")
        Log.d("ABCD", "theDayAfterEndDate = ${theDayAfterEndDate}")
        return date == startDate || (date.after(startDate) && date.before(theDayAfterEndDate)) //compare endDate with date+1
    } catch (e: ParseException) {
        return false
    }
    return false
}

@Composable
fun AllTasks(
    navController: NavController,
    viewModel: TaskViewModel,

){
    val scaffoldState : ScaffoldState = rememberScaffoldState(/*rememberDrawerState(DrawerValue.Closed)*/)

    Scaffold (

        scaffoldState = scaffoldState,
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
        content = {
            AlltasksBodyContent(navController, viewModel)
        },
        bottomBar = {

            AllTasksBottomBar(navController)
        }
    )
}


@Composable
fun AlltasksBodyContent(navController: NavController, viewModel: TaskViewModel) {
    val tasksList by viewModel.allProducts.observeAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if(tasksList == null){
            Text(text = "No task yet, go and add 1!")
        }

        Spacer(modifier = Modifier.height(50.dp))
        Text(
            text = "In Progress:",
            fontSize = 30.sp,
            textAlign = TextAlign.Right
        )
        tasksList?.forEach { task ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) { if(CompareDateAllTasks(task)) {
                        Text(text = "${task.taskName}")
                        Text(text = "${task.taskType}")
                        IconButton(onClick = { navController.navigate(NavScreen.SubtaskDetail.route) }) {
                            Icon(
                                Icons.Default.Menu,
                                contentDescription = "Menu"
                            )
                        }
                        IconButton(onClick = { navController.navigate(NavScreen.CountingTime.route + "/${task.id}") }) {
                            Icon(
                                Icons.Default.PlayArrow,
                                contentDescription = "PlayButton"
                            )
                        }
                    }
                }
            }

        Spacer(modifier = Modifier.height(40.dp))

//        Text(
//            text = "Recommend to Start:",
//            fontSize = 30.sp,
//            textAlign = TextAlign.Right
//        )
//        Row(
//            horizontalArrangement = Arrangement.spacedBy(20.dp),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Text(text = "CA Final")
//            IconButton(onClick = { navController.navigate(NavScreen.SubtaskDetail.route) }) {
//                Icon(
//                    Icons.Default.Menu,
//                    contentDescription = "Menu"
//                )
//            }
//            IconButton(onClick = { navController.navigate(NavScreen.CountingTime.route) }) {
//                Icon(
//                    Icons.Default.PlayArrow,
//                    contentDescription = "PlayButton"
//                )
//            }
//        }
//
        Text(
            text = "Not Started:",
            fontSize = 30.sp,
            textAlign = TextAlign.Right
        )
        tasksList?.forEach { task ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ){ if(!CompareDateAllTasks(task)) {
                    Text(text = "${task.taskName}")
                    IconButton(onClick = { navController.navigate(NavScreen.SubtaskDetail.route) }) {
                        Icon(
                            Icons.Default.Menu,
                            contentDescription = "Menu"
                        )
                    }
                    IconButton(onClick = { navController.navigate(NavScreen.CountingTime.route + "/${task.id}") }) {
                        Icon(
                            Icons.Default.PlayArrow,
                            contentDescription = "PlayButton"
                        )
                    }
                }
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

/*@Preview(showBackground = true)
@Composable
fun AllTasksPreview() {
    WorkOrDieTheme {
        AllTasks(rememberNavController())
    }
}*/