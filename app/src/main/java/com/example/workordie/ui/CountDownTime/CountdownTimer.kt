package com.example.workordie.ui.CountDownTime

import android.os.Bundle
import android.widget.EditText
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

import com.example.workordie.R
import com.example.workordie.ui.CountDownTime.CountDownTimeViewModel
import com.example.workordie.ui.CountDownTime.utils.TimeFormatUtils
import com.example.workordie.ui.screen.NavScreen
import com.example.workordie.ui.theme.WorkOrDieTheme
import kotlinx.coroutines.CoroutineScope
import kotlin.math.cos
import kotlin.math.sin


// Start building your app here!
@Composable
fun CountdownTimer(navController : NavController) {
    val viewModel: CountDownTimeViewModel = viewModel()
    val scaffoldState : ScaffoldState = rememberScaffoldState(/*rememberDrawerState(DrawerValue.Closed)*/)



    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
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
                }
            )
        },
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Countdown", fontSize = 30.sp)
            CompletedText(viewModel)
            TimeLeftText(viewModel)
            ProgressCircle(viewModel)
            EditText(viewModel)
            Row {
                StartButton(viewModel)
                StopButton(viewModel)
            }

            Button(
                onClick = { navController.navigate(NavScreen.FinishPopup.route) },
                modifier = Modifier
                    .width(150.dp)
                    .padding(16.dp))
                {
                    Text(text = "finish")
                }
        }
    }
}

@Composable
private fun TimeLeftText(viewModel: CountDownTimeViewModel) {
    Text(
        text = TimeFormatUtils.formatTime(viewModel.timeLeft),
        modifier = Modifier.padding(16.dp)
    )
}

@Composable
private fun EditText(viewModel: CountDownTimeViewModel) {
    Box(
        modifier = Modifier
            .size(300.dp, 120.dp),
        contentAlignment = Alignment.Center
    ) {
        if (viewModel.status.showEditText()) {
            TextField(
                modifier = Modifier
                    .size(200.dp, 60.dp),
                value = if (viewModel.totalTime == 0L) "" else viewModel.totalTime.toString(),
                onValueChange = viewModel::updateValue,
                label = { Text("Countdown Seconds") },
                maxLines = 1,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }
    }
}

@Composable
private fun StartButton(viewModel: CountDownTimeViewModel) {
    Button(
        modifier = Modifier
            .width(150.dp)
            .padding(16.dp),
        enabled = viewModel.totalTime > 0,
        onClick = viewModel.status::clickStartButton,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFFFBE8A6),
            contentColor = Color.Black)
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
        onClick = viewModel.status::clickStopButton,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFFFBE8A6),
            contentColor = Color.Black)
    ) {
        Text(text = "Stop")
    }
}

@Composable
fun ProgressCircle(viewModel:CountDownTimeViewModel) {
    // Circle diameter
    val size = 160.dp
    Box(contentAlignment = Alignment.Center) {
        Canvas(
            modifier = Modifier.size(size)
        ) {
            val sweepAngle = viewModel.status.progressSweepAngle()
            // Circle radius
            val r = size.toPx() / 2
            // The width of Ring
            val stokeWidth = 12.dp.toPx()
            // Draw dial plate
            drawCircle(
                color = Color.LightGray,
                style = Stroke(
                    width = stokeWidth,
                    pathEffect = PathEffect.dashPathEffect(
                        intervals = floatArrayOf(1.dp.toPx(), 3.dp.toPx())
                    )
                )
            )
            // Draw ring
            drawArc(
                brush = Brush.sweepGradient(
                    0f to Color.Magenta,
                    0.5f to Color.Blue,
                    0.75f to Color.Green,
                    0.75f to Color.Transparent,
                    1f to Color.Magenta
                ),
                startAngle = -90f,
                sweepAngle = sweepAngle,
                useCenter = false,
                style = Stroke(
                    width = stokeWidth
                ),
                alpha = 0.5f
            )
            // Pointer
            val angle = (360 - sweepAngle) / 180 * Math.PI
            val pointerTailLength = 8.dp.toPx()
            drawLine(
                color = Color.Red,
                start = Offset(r + pointerTailLength * sin(angle).toFloat(), r + pointerTailLength * cos(angle).toFloat()),
                end = Offset((r - r * sin(angle) - sin(angle) * stokeWidth / 2).toFloat(), (r - r * cos(angle) - cos(angle) * stokeWidth / 2).toFloat()),
                strokeWidth = 2.dp.toPx()
            )
            drawCircle(
                color = Color.Red,
                radius = 5.dp.toPx()
            )
            drawCircle(
                color = Color.White,
                radius = 3.dp.toPx()
            )
        }
    }
}

@Composable
private fun CompletedText(viewModel:CountDownTimeViewModel) {
    Text(
        text = viewModel.status.completedString(),
        color = MaterialTheme.colors.primary
    )
}
@Preview(showBackground = true)
@Composable
fun CountdownTimePreview() {
    WorkOrDieTheme {
        CountdownTimer(rememberNavController())
    }
}
