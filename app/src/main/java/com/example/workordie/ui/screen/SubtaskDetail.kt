package com.example.workordie.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.workordie.ui.theme.WorkOrDieTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun SubtaskDetail(navController : NavController){
    val scaffoldState : ScaffoldState = rememberScaffoldState()
    val scope : CoroutineScope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            SubtaskTopBar(scaffoldState = scaffoldState, scope = scope)
        },
        content = {
            SubtaskContent(navController)
        }
    )
}
/*https://developer.android.com/jetpack/compose/layouts/alignment-lines?hl=zh-tw*/
@Composable
fun SubtaskTopBar(scaffoldState : ScaffoldState, scope : CoroutineScope){
    val drawerState = scaffoldState.drawerState

    TopAppBar(
        title = { },
        modifier = Modifier.fillMaxWidth(),
        navigationIcon = {
            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.End
            ){
                //TODO: change to BACK button
                IconButton(
                    onClick = {
                        scope.launch {
                            if(drawerState.isClosed){
                                drawerState.open()
                            }else{
                                drawerState.close()
                            }
                        }
                    }
                ) {
                    Icon(
                        Icons.Default.Settings,
                        contentDescription = "Settings"
                    )
                }
            }
        }
    )
}

@Composable
fun SubtaskContent(navController : NavController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Task 1",
            fontWeight = FontWeight.ExtraBold,
            color = MaterialTheme.colors.onSurface,
            fontSize = 35.sp
        )
        Spacer(modifier = Modifier.height(12.dp))
        CheckSubtasks()
        Spacer(modifier = Modifier.height(48.dp))
        // TODO: + Add subtask button
        Button(
            onClick = {/*TODO*/}
        ) {
            Text(text = "+ Add subtask")
        }
        Spacer(modifier = Modifier.height(48.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Icon(
                Icons.Filled.Star,
                contentDescription = "completion icon"
            )
            Text(text = "Completion:",
                color = MaterialTheme.colors.onPrimary,
                fontSize = 24.sp
            )
            /*TODO: update completion from DB*/
            Text(text = "40",
                color = MaterialTheme.colors.onPrimary,
                fontSize = 24.sp
            )
            Text(text = "%",
                color = MaterialTheme.colors.onPrimary,
                fontSize = 24.sp
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                Icons.Filled.Event,
                contentDescription = "Due date"
            )
            Spacer(Modifier.width(10.0.dp))
            Text(text = "Deadline:",
                textAlign = TextAlign.Start,
                color = MaterialTheme.colors.onPrimary,
                fontSize = 24.sp
            )
            /*TODO: update completion from DB*/
            Text(text = "<date>",
                color = MaterialTheme.colors.onPrimary,
                fontSize = 24.sp
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                Icons.Filled.QueryBuilder,
                contentDescription = "hrs per day"
            )
            Spacer(Modifier.width(10.0.dp))
            Text(text = "Hours/day:",
                color = MaterialTheme.colors.onPrimary,
                fontSize = 24.sp
            )
            /*TODO: update completion from DB*/
            Text(text = "<Hrs>",
                color = MaterialTheme.colors.onPrimary,
                fontSize = 24.sp
            )
        }
    }
}
@Composable
private fun CheckSubtasks(names: List<String> = List(2) { "$it" }) {
    LazyColumn{
        items(items = names) {name ->
            CheckSubtask(name = name)
        }
    }
}

@Composable
private fun CheckSubtask(name: String) {
    Column(
        //modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(/*TODO*/
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            SubtaskCheckbox()
            Text(text = "Subtask",
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.onPrimary,
                fontSize = 24.sp,
                /*TODO: should fix the check box start location & reorder button,
                *  rather than setting the padding of the end of subtask*/
                modifier = Modifier.padding(end = 60.dp)
            )
            /*TODO: float icon??*/
            Icon(
                Icons.Filled.Reorder,
                contentDescription = "Reorder button"
            )
        }
    }
}

@Composable
fun SubtaskCheckbox() {
    val isChecked = remember { mutableStateOf(false) }

    Checkbox(checked = isChecked.value,
        onCheckedChange = { isChecked.value = it },
        colors = CheckboxDefaults.colors(MaterialTheme.colors.onPrimary)
    )
}

@Preview(showBackground = true)
@Composable
fun SubtaskDetailPreview() {
    WorkOrDieTheme {
        SubtaskDetail(rememberNavController())
    }
}