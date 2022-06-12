package com.example.workordie.ui.screen


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.text.input.KeyboardType
import com.example.workordie.TaskViewModel
import com.example.workordie.model.Task


/* TODO
* add subtask text field
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

    val scaffoldState : ScaffoldState = rememberScaffoldState(/*rememberDrawerState(DrawerValue.Closed)*/)
    Scaffold (scaffoldState = scaffoldState) {
        TopAppBar(
            title = { },
            modifier = Modifier,
            navigationIcon = {
                IconButton(
                    onClick = { navController.popBackStack() }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "back"
                    )
                }
            }
        )

        Column(
            modifier = Modifier
                .padding(70.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ){
            Text(
                text = "New Task",
                modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                fontSize = 36.sp
            )
            var expanded by remember { mutableStateOf(false) }
            val items = listOf("Choose Subject", "CA", "SS", "Prob", "GE", "English")
            val disabledValue = "GE"
            var selectedIndex by remember { mutableStateOf(0) }
            Box() {
                Text(
                    text = items[selectedIndex],
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(onClick = { expanded = true })
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items.forEachIndexed { index, s ->
                        DropdownMenuItem(onClick = {
                            selectedIndex = index
                            taskNameState = items[index]
                            expanded = false
                        }) {
                            val disabledText = if (s == disabledValue) {
                                " (Disabled)"
                            } else {
                                ""
                            }
                            Text(text = s + disabledText)
                        }
                    }
                }
            }

            OutlinedTextField(
                value = taskNameState,
                onValueChange = { taskNameState = it },
                label = { Text(text = "Name") },
                placeholder = { Text(text = "Click Choose Subject") },
                readOnly = true
            )
            OutlinedTextField(
                value = taskTypeState,
                onValueChange = { taskTypeState = it },
                label = { Text(text = "Type") },
                placeholder = { Text(text = "ex. HW") }
            )
            OutlinedTextField(
                value = taskDeadlineState,
                onValueChange = { taskDeadlineState = it },
                label = { Text(text = "Deadline") },
                placeholder = { Text(text = "ex. 5/30") }
            )
            OutlinedTextField(
                value = taskTimeState,
                onValueChange = { taskTimeState = it },
                label = { Text(text = "Estimated Time to Finish(in hour)") },
                placeholder = { Text(text = "ex. 5") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            OutlinedTextField(
                value = taskStartDateState,
                onValueChange = { taskStartDateState = it },
                label = { Text(text = "Estimated Start Date") },
                placeholder = { Text(text = "ex. 5/20") }
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
                        totalTimeSpent = 0.0f
                    )
                    viewModel.insert(task)
                    navController.navigate(NavScreen.FinishSubmit.route + "/${taskNameState}/${taskTypeState}")
                },
                modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                enabled = (
                        taskNameState != "" &&
                        taskTypeState != "" &&
                        taskStartDateState != "" &&
                        taskTimeState != "" &&
                        taskDeadlineState != "" )
            ) {
                Text(text = "submit")
            }
        }
    }

}


/*@Preview(showBackground = true)
@Composable
fun AddTaskPreview() {
    WorkOrDieTheme {
        AddTask(rememberNavController(), viewModel = TaskViewModel())
    }
}*/