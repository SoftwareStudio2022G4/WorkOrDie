package com.example.workordie.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.workordie.ui.CountDownTime.CountDownTimeViewModel
import com.example.workordie.ui.theme.WorkOrDieTheme

@Composable
fun CountingTime(modifier: Modifier = Modifier){
    androidx.compose.material.Surface(color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)) {
        Column(Modifier.padding(20.dp))
        {
            Row(){

                Button(
                    onClick = {  },
                    modifier = Modifier
                        .padding(start = 30.dp, end = 40.dp, top = 10.dp)
                        .wrapContentWidth(Alignment.CenterHorizontally)
                ) {
                    Text(text = "Countdown")
                }
                Button(
                    onClick = {  },
                    modifier = modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally)
                        .padding( start = 10.dp,end = 10.dp, top = 10.dp)
                ) {
                    Text(text = "Accumulate")
                }

            }
            Text(
                text = "Softwre Studio",
                textAlign = TextAlign.End,
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally)
                    .padding(vertical = 30.dp),
            )
            TextField(
                value = "00 : 00 : 00",
                onValueChange = {},
                modifier = modifier.padding(horizontal = 30.dp)

            )
            TextField(
                value = "Hr : min : sec",
                onValueChange = {},
                modifier = modifier.padding(horizontal = 30.dp),
            )
            val viewModel: CountDownTimeViewModel = viewModel()
            Row {
                StartButton(viewModel)
                StopButton(viewModel)
            }
            Text(
                text = "Subtask1",
                textAlign = TextAlign.Right,
                modifier = modifier.padding(vertical = 10.dp),
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
        CountingTime()
    }
}