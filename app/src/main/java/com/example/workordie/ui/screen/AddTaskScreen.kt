package com.example.workordie.ui.screen


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.text.input.KeyboardType
import com.example.workordie.TaskViewModel
import com.example.workordie.model.Task


/* TODO
*  add subtask textfield
* */
@Composable
fun AddTask(
    navController: NavController,
    viewModel: TaskViewModel
){
    var taskNameState by rememberSaveable { mutableStateOf("") }
    var taskTypeState by rememberSaveable { mutableStateOf("") }
    var taskDeadlineState by rememberSaveable { mutableStateOf("") }
    var taskTimeState by rememberSaveable { mutableStateOf("") }
    var taskStartDateState by rememberSaveable { mutableStateOf("") }

    TopAppBar(
        title = { },
        modifier = Modifier,
        navigationIcon = {
            IconButton(
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "back"
                )
            }
        }
    )

    Column(
        modifier = Modifier.padding(70.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        Text(
            text = "New Task",
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
            fontSize = 36.sp
        )
        OutlinedTextField(
            value = taskNameState,
            onValueChange = { taskNameState = it },
            label = { Text(text = "Name") },
            placeholder = { Text(text = "Probability") }
        )
        OutlinedTextField(
            value = taskTypeState,
            onValueChange = { taskTypeState = it },
            label = { Text(text = "Type") },
            placeholder = { Text(text = "HW") }
        )
        OutlinedTextField(
            value = taskDeadlineState,
            onValueChange = { taskDeadlineState = it },
            label = { Text(text = "Deadline") },
            placeholder = { Text(text = "5/30") }
        )
        OutlinedTextField(
            value = taskTimeState,
            onValueChange = { taskTimeState = it },
            label = { Text(text = "Estimated Time to Finish(in hour)") },
            placeholder = { Text(text = "5") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        OutlinedTextField(
            value = taskStartDateState,
            onValueChange = { taskStartDateState = it },
            label = { Text(text = "Estimated Start Date") },
            placeholder = { Text(text = "5/20") }
        )
        OutlinedTextField(
            value = "+",
            onValueChange = { },
            label = { Text(text = "Add subtasks") }
        )
        Button(
            onClick = {
                val task = Task(
                    taskType = taskTypeState,
                    taskName = taskNameState,
                    startDate = taskStartDateState,
                    endDate = taskDeadlineState,
                    totalTimeSpent = 0.0
                )
                viewModel.insert(task)
                navController.navigate(NavScreen.FinishSubmit.route)
            },
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
        ) {
            Text(text = "submit")
        }
    }

}


/*@Preview(showBackground = true)
@Composable
fun AddTaskPreview() {
    WorkOrDieTheme {
        AddTask()
    }
}*/