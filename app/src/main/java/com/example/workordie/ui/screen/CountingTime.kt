package com.example.workordie.ui.screen

import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.workordie.ui.CountDownTime.CountDownTimeViewModel

import com.example.workordie.ui.accumulateTime.AccumulateTimeViewModel
import com.example.workordie.ui.theme.WorkOrDieTheme
import kotlinx.coroutines.CoroutineScope
import kotlin.time.ExperimentalTime

@Composable
fun CountingTime(modifier: Modifier = Modifier, navController : NavController){
    val scaffoldState : ScaffoldState = rememberScaffoldState(/*rememberDrawerState(DrawerValue.Closed)*/)
    Scaffold(
        scaffoldState = scaffoldState,

        floatingActionButton = {
            FloatingActionButton(
                onClick = {navController.navigate(NavScreen.Home.route)}
            ){
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "home icon"
                )
            }
        },

        content = {
            CountingTimeBody(modifier, navController = navController)
        },

    )
}
@Composable
fun CountingTimeBody(modifier : Modifier = Modifier, navController : NavController){
    //var mode = remember { mutableStateOf(0) }
    Column(Modifier.padding(20.dp))
    {
        Text(
            text = "Softwre Studio",
            textAlign = TextAlign.End,
            modifier = modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
                .padding(vertical = 30.dp),
        )
        
        //val viewModel: MainViewModel = viewModel()

        Row {
            Spacer(modifier = Modifier.width(30.dp))
            Button(
                onClick = { navController.navigate(NavScreen.CountdownTime.route) },
                modifier = Modifier
                    .width(150.dp)
                    .padding(16.dp)
            ) {
                Text(text = "Countdown")
            }
            Button(
                onClick = { navController.navigate(NavScreen.AccumulateTime.route) },
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
            modifier = modifier.padding(vertical = 10.dp),
        )
        Text(
            text = "Subtask3",
            textAlign = TextAlign.Right,
            modifier = modifier.padding(vertical = 10.dp),
        )
        Text(
            text = "Subtask4",
            textAlign = TextAlign.Right,
            modifier = modifier.padding(vertical = 10.dp),

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
@Preview(showBackground = true)
@Composable
fun CountingTimePreview() {
    WorkOrDieTheme {
        CountingTime(navController = rememberNavController())
    }
}