package com.example.workordie.ui.screen


import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.workordie.TaskViewModel
import com.example.workordie.model.Task
import com.example.workordie.ui.CountDownTime.CountDownTimeViewModel
import com.example.workordie.ui.accumulateTime.AccumulateTimeViewModel
import com.example.workordie.ui.theme.WorkOrDieTheme
import kotlinx.coroutines.CoroutineScope


@Composable
fun CountingTime(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: TaskViewModel,
    pickedId: String?
){
    val scaffoldState : ScaffoldState = rememberScaffoldState(/*rememberDrawerState(DrawerValue.Closed)*/)
    Scaffold(
        scaffoldState = scaffoldState,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(NavScreen.Home.route) }
            ){
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "home icon"
                )
            }
        },
        content = {
            CountingTimeBody(
                modifier = modifier,
                navController = navController,
                viewModel = viewModel,
                pickedId = pickedId
            )
        }
    )
}
@Composable
fun CountingTimeBody(
    modifier : Modifier = Modifier,
    navController : NavController,
    viewModel: TaskViewModel,
    pickedId: String?
){
    //var mode = remember { mutableStateOf(0) }
    val pickedIdReal = pickedId!!.toInt()
    val taskTodo by viewModel.searchResults.observeAsState()
    viewModel.findSingleDayTask(pickedIdReal)
//    Log.d("ABCD", "pickidReal = $pickedIdReal")
    var task: Task? = null
    try {
        task = taskTodo?.get(0)
//        Log.d("ABCD", "taskName = ${task?.taskName}")
    } catch (e: IndexOutOfBoundsException) {
        task?.taskName = "Loading..."
    }


    Column(Modifier.padding(20.dp)) {
        Text(
            text = "${task?.taskName}",
            textAlign = TextAlign.End,
            modifier = modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
                .padding(vertical = 30.dp)
        )
        
        //val viewModel: MainViewModel = viewModel()

        Row {
            Spacer(modifier = Modifier.width(30.dp))
            Button(
                onClick = { navController.navigate(NavScreen.CountdownTime.route + "/${task?.id}") },
                modifier = Modifier
                    .width(150.dp)
                    .padding(16.dp)
            ) {
                Text(text = "Countdown")
            }
            Button(
                onClick = {
                    navController.navigate(NavScreen.AccumulateTime.route + "/${task?.id}")
                  },
                modifier = Modifier
                    .width(150.dp)
                    .padding(16.dp)
            ) {
                Text(text = "Accumulate")
            }
            //StartButton(viewModel)
            //StopButton(viewModel)

        }
        /*if(mode.value == 0){
        MyApp()
    }
    else{
        MainApp(viewModel = viewModel)
    }*/
        Text(
            text = "Subtask1",
            textAlign = TextAlign.Right,
            modifier = modifier.padding(vertical = 10.dp)
        )
        Text(
            text = "Subtask2",
            textAlign = TextAlign.Right,
            modifier = modifier.padding(vertical = 10.dp)
        )
        Text(
            text = "Subtask3",
            textAlign = TextAlign.Right,
            modifier = modifier.padding(vertical = 10.dp)
        )
        Text(
            text = "Subtask4",
            textAlign = TextAlign.Right,
            modifier = modifier.padding(vertical = 10.dp)
        )
    }
}

@Composable
private fun StartButton(viewModel: CountDownTimeViewModel) {
    Button(
        modifier = Modifier
            .width(150.dp)
            .padding(16.dp),
        enabled = viewModel.totalTime > 0,
        onClick = viewModel.status::clickStartButton
    ) {
        Text(text = viewModel.status.startButtonDisplayString())
    }
}

@Composable
private fun StopButton(viewModel: CountDownTimeViewModel) {
    Button(
        modifier = Modifier
            .width(150.dp)
            .padding(16.dp),
        enabled = viewModel.status.stopButtonEnabled(),
        onClick = viewModel.status::clickStopButton
    ) {
        Text(text = "Stop")
    }
}
//@Preview(showBackground = true)
//@Composable
//fun CountingTimePreview() {
//    WorkOrDieTheme {
//        CountingTime(navController = rememberNavController())
//    }
//}