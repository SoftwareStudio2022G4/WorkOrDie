package com.example.workordie.ui.accumulateTime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.workordie.R
import com.example.workordie.TaskViewModel
import com.example.workordie.ui.CountDownTime.CountDownTimeViewModel
import com.example.workordie.ui.screen.NavScreen
import kotlinx.coroutines.CoroutineScope
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
@Composable
fun AccumulateTimer(
    viewModel: AccumulateTimeViewModel,
    navController: NavController,
    pickedId: String?,
    taskViewModel: TaskViewModel
) {
    AccumulateTimerContent(
        navController = navController,
        isPlaying = viewModel.isPlaying,
        seconds = viewModel.seconds,
        minutes = viewModel.minutes,
        hours = viewModel.hours,
        onStart = { viewModel.start() },
        onPause = { viewModel.pause() },
        onStop = { viewModel.stop() },
        timeSpent = viewModel.timeSpent,
        pickedId = pickedId,
        taskViewModel = taskViewModel
    )
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun AccumulateTimerContent(
    navController : NavController,
    isPlaying: Boolean,
    seconds: String,
    minutes: String,
    hours: String,
    onStart: () -> Unit = {},
    onPause: () -> Unit = {},
    onStop: () -> Unit = {},
    timeSpent: Long,
    pickedId: String?,
    taskViewModel: TaskViewModel
) {
    val scaffoldState : ScaffoldState = rememberScaffoldState(/*rememberDrawerState(DrawerValue.Closed)*/)

    Scaffold(scaffoldState = scaffoldState) {
        TopAppBar(
            title = {
                Text(text = stringResource(id = R.string.app_name))
            },
            navigationIcon = {
                IconButton(onClick = { navController.navigate(NavScreen.CountingTime.route + "/${pickedId}") }) {
                    Icon(Icons.Filled.ArrowBack, "backIcon")
                }
            }
        )

        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Row {
                val numberTransitionSpec: AnimatedContentScope<String>.() -> ContentTransform = {
                    slideInVertically(
                        initialOffsetY = { it }
                    ) + fadeIn() with slideOutVertically(
                        targetOffsetY = { -it }
                    ) + fadeOut() using SizeTransform(
                        false
                    )
                }

                CompositionLocalProvider(LocalTextStyle provides MaterialTheme.typography.h3) {
                    AnimatedContent(targetState = hours, transitionSpec = numberTransitionSpec) {
                        Text(text = it)
                    }
                    Text(text = ":")
                    AnimatedContent(targetState = minutes, transitionSpec = numberTransitionSpec) {
                        Text(text = it)
                    }
                    Text(text = ":")
                    AnimatedContent(targetState = seconds, transitionSpec = numberTransitionSpec) {
                        Text(text = it)
                    }
                }
            }
            //Spacer(modifier = Modifier.height(50.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                AnimatedContent(targetState = isPlaying) {
                    if (it)
                        Button(
                            onClick = onPause,
                            modifier = Modifier
                                .width(150.dp)
                                .padding(16.dp)
                        ) {
                            Text(text = "pause")
                        }
                    else
                        Button(
                            onClick = onStart,
                            modifier = Modifier
                                .width(150.dp)
                                .padding(16.dp)
                        ) {
                            Text(text = "start")
                        }
                }


                Button(
                    onClick = onStop,
                    modifier = Modifier
                        .width(150.dp)
                        .padding(16.dp)
                ) {
                    Text(text = "stop")
                }
            }
            for(i in 1..4){
                val isChecked = remember { mutableStateOf(false) }
                Row(
                    modifier = Modifier
                        .clickable(
                            onClick = {
                                isChecked.value = !isChecked.value // 點擊文字時也能變更勾選狀態
                            }
                        )
                        .padding(8.dp)
                ) {
                    Checkbox(
                        checked = isChecked.value, // 是否勾選
                        enabled = true, // 能否被變更
                        onCheckedChange = { checked ->
                            isChecked.value = checked
                        },
                        colors = CheckboxDefaults.colors(
                            checkedColor = MaterialTheme.colors.primarySurface, // 勾選時顏色
                            uncheckedColor = MaterialTheme.colors.primary, // 未勾選時顏色
                        )
                    )
                    Text(
                        text = "Subtask $i",
                        color = MaterialTheme.colors.secondaryVariant,
                        modifier = Modifier
                            .padding(start = 5.dp)
                            .align(Alignment.CenterVertically)
                    )
                }
            }

            Spacer(modifier = Modifier.height(180.dp))
            Button(
                onClick = {
                    taskViewModel.updateTaskTimeSpent(timeSpent, pickedId!!.toInt())
                    navController.navigate(NavScreen.FinishPopup.route + "/${pickedId}")
                },
                modifier = Modifier
                    .width(150.dp)
                    .padding(16.dp)
            ) {
                Text(text = "finish")
            }
        }
    }
}



//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//
//        AccumulateTimerContent(
//            navController = rememberNavController(),
//            false,
//            "00",
//            "00",
//            "00"
//        )
//
//}
