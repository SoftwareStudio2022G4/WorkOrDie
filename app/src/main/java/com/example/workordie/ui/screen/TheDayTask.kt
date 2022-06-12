package com.example.workordie.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.workordie.TaskViewModel
import com.example.workordie.model.Task
import com.example.workordie.ui.theme.WorkOrDieTheme
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

private fun reFormater(pickedDate: String?) : String{
    val originalDateFormat = SimpleDateFormat("yyyy-MM-dd")
    val dateformat = SimpleDateFormat("yyyy/MM/dd")
    val date = originalDateFormat.parse(pickedDate)
    val dateString = dateformat.format(date)
    return dateString
}

private fun CompareDateCalender(task: Task, dateString: String): Boolean {
    //today
    val dateformat = SimpleDateFormat("yyyy/MM/dd")
    val date = dateformat.parse(dateString)

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
fun DailyTask(
    navController: NavController,
    viewModel: TaskViewModel,
    pickedDate: String?
){
    val dateString = reFormater(pickedDate)

    val scaffoldState : ScaffoldState = rememberScaffoldState(/*rememberDrawerState(DrawerValue.Closed)*/)
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {
                    IconButton(onClick = { navController.navigate(NavScreen.Calendar.route) }) {
                        Icon(Icons.Filled.ArrowBack, "backIcon")
                    }

                    Spacer(modifier = Modifier.width(240.dp))

                    Text(text = "${dateString}'s task")
                },
            )
        },
        content = { DailyTaskContent(navController, viewModel, dateString) }
    )
}


@Composable
fun DailyTaskContent(
    navController: NavController,
    viewModel: TaskViewModel,
    dateString: String
){
    val allTaskList by viewModel.allProducts.observeAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Tasks:",
            fontSize = 30.sp,
            textAlign = TextAlign.Right
        )

        allTaskList?.forEach{ task ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                if(CompareDateCalender(task, dateString)) {
                    Text(text = "${task.taskName}")
                    Text(text = "${task.totalTimeSpent} h")
                    IconButton(onClick = { navController.navigate(NavScreen.SubtaskDetail.route)}) {
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


/*@Preview(showBackground = true)
@Composable
fun DailyTaskPreview() {
    WorkOrDieTheme {
        DailyTask(rememberNavController())
    }
}*/