package com.example.workordie.ui.accumulateTime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.PlayArrow

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.workordie.R
import com.example.workordie.ui.CountDownTime.CountDownTimeViewModel
import com.example.workordie.ui.screen.NavScreen
import kotlinx.coroutines.CoroutineScope
import kotlin.time.ExperimentalTime

@ExperimentalTime
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //MainApp(viewModel, navController = )


        }
    }
}

@OptIn(ExperimentalTime::class)
@Composable
fun MainApp(viewModel: MainViewModel, navController: NavController) {
    MainApp(
        navController,
        isPlaying = viewModel.isPlaying,
        seconds = viewModel.seconds,
        minutes = viewModel.minutes,
        hours = viewModel.hours,
        onStart = { viewModel.start() },
        onPause = { viewModel.pause() },
        onStop = { viewModel.stop() }
    )
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun MainApp(
    navController : NavController,
    isPlaying: Boolean,
    seconds: String,
    minutes: String,
    hours: String,
    onStart: () -> Unit = {},
    onPause: () -> Unit = {},
    onStop: () -> Unit = {},
) {
    val scaffoldState : ScaffoldState = rememberScaffoldState(/*rememberDrawerState(DrawerValue.Closed)*/)
    val scope : CoroutineScope = rememberCoroutineScope()
    Scaffold(Modifier.fillMaxSize()) {

        TopAppBar(
            title = {
                    Text(
                        text = stringResource(id = R.string.app_name)
                    )
                },
            navigationIcon = {
                IconButton(onClick = {navController.navigate(NavScreen.CountingTime.route)}) {
                    Icon(Icons.Filled.ArrowBack, "backIcon")

                }
            },
            backgroundColor = Color(0xFFFBE8A6),
            contentColor = Color.Black
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
            Spacer(modifier = Modifier.height(200.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),

            ) {
                AnimatedContent(targetState = isPlaying) {
                    if (it)
                        Button(
                            onClick = onPause,
                            modifier = Modifier
                                .width(150.dp)
                                .padding(16.dp),) {
                            Text(text = "pause")
                        }
                    else
                        Button(
                            onClick = onStart,
                            modifier = Modifier
                                .width(150.dp)
                                .padding(16.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color(0xFFFBE8A6),
                                contentColor = Color.Black)) {
                            Text(text = "start")
                        }
                }
                Button(
                    onClick = onStop,
                    modifier = Modifier
                        .width(150.dp)
                        .padding(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFFFBE8A6),
                        contentColor = Color.Black)

                ) {
                    Text(text = "stop")
                }

            }
            Button(
                onClick = { navController.navigate(NavScreen.FinishPopup.route) },
                modifier = Modifier
                    .width(150.dp)
                    .padding(16.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFFFBE8A6),
                    contentColor = Color.Black)

            ) {
                Text(text = "finish")
            }
            Spacer(modifier = Modifier.height(232.dp))
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

        MainApp(
            navController = rememberNavController(),
            false,
            "00",
            "00",
            "00"
        )

}
