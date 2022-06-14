package com.example.workordie.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.workordie.ui.theme.WorkOrDieTheme

/*TODO (1) Add horizontal line below Add subtask button
       (2) Alight the 3 icons below
       (3) Fix checkboxes so it won't move according to the length of Subtask name
       (4) Navigation of 3 button and Go back button
       (5) Connect and update data according to DB
       (6) Add description of Completion*/

@Composable
fun SubtaskDetail(navController : NavController){
    val scaffoldState : ScaffoldState = rememberScaffoldState()
    rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            SubtaskTopBar(scaffoldState = scaffoldState, navController)
        },
        content = {
            SubtaskContent(navController)
        }
    )
}

@Composable
fun SubtaskTopBar(scaffoldState: ScaffoldState, navController: NavController){
    scaffoldState.drawerState

    TopAppBar(
        title = { },
        modifier = Modifier.fillMaxWidth(),
        navigationIcon = {
            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.End
            ){
                IconButton(
                    onClick = {navController.navigate(NavScreen.Home.route) }
                ) {
                    Icon(
                        Icons.Filled.ArrowBackIos,
                        contentDescription = "back"
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
            //don't set the color!! it is set already
            //or the word may disappear
            fontSize = 35.sp
        )
        Spacer(modifier = Modifier.height(12.dp))
        CheckSubtasks()
        Spacer(modifier = Modifier.height(48.dp))
        val backgroundShape = RoundedCornerShape(6.dp)
        Button(
            onClick = {},
            /*modifier = Modifier.width(220.dp).height(60.dp)*/
            contentPadding = PaddingValues(
                start = 30.dp,
                top = 16.dp,
                end = 30.dp,
                bottom = 16.dp
            ),
            modifier = Modifier.shadow(8.dp, backgroundShape),
            shape = RoundedCornerShape(6.dp)
        ) {
            Icon(
                Icons.Filled.Add,
                contentDescription = "Add",
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            Text(text = "Add subtask",
            fontSize = 20.sp)
        }
        Spacer(modifier = Modifier.height(48.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Icon(
                Icons.Filled.StarBorder,
                contentDescription = "completion icon"
            )
            Text(text = "Completion: ",
                //color = MaterialTheme.colors.onPrimary,
                fontSize = 24.sp
            )
            /*TODO (5): update completion from DB*/
            Text(text = "40",
                //color = MaterialTheme.colors.onPrimary,
                fontSize = 24.sp
            )
            Text(text = "%",
                //color = MaterialTheme.colors.onPrimary,
                fontSize = 24.sp
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                Icons.Filled.Event,
                contentDescription = "Due date"
            )
            Spacer(Modifier.width(10.0.dp))
            Text(text = "Deadline: ",
                textAlign = TextAlign.Start,
                //color = MaterialTheme.colors.onPrimary,
                fontSize = 24.sp
            )
            /*TODO (5): update completion from DB*/
            Text(text = "<date>",
                //color = MaterialTheme.colors.onPrimary,
                fontSize = 24.sp
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                Icons.Filled.QueryBuilder,
                contentDescription = "hrs per day"
            )
            Spacer(Modifier.width(10.0.dp))
            Text(text = "Hours/day: ",
                //color = MaterialTheme.colors.onPrimary,
                fontSize = 24.sp
            )
            /*TODO: update completion from DB*/
            Text(text = "<Hrs>",
                //color = MaterialTheme.colors.onPrimary,
                fontSize = 24.sp
            )
        }
        Spacer(modifier = Modifier.height(180.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = { navController.navigate(NavScreen.AllTasks.route) },
                modifier = Modifier
                    .width(150.dp)
                    .height(48.dp)
                    .shadow(8.dp, backgroundShape),
                shape = RoundedCornerShape(6.dp)
            ) {
                Text(text = "Save",
                    fontSize = 20.sp)
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Icon(
                    Icons.Filled.Save,
                    contentDescription = "save",
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
            }
            Spacer(modifier = Modifier.width(20.dp))
            Button(onClick = { navController.navigate(NavScreen.CountingTime.route) },
                modifier = Modifier
                    .width(150.dp)
                    .height(48.dp)
                    .shadow(8.dp, backgroundShape),
                shape = RoundedCornerShape(6.dp)
            ) {
                Text(text = "Start now",
                    fontSize = 20.sp)
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Icon(
                    Icons.Filled.PlayArrow,
                    contentDescription = "start",
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
            }
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
                //color = MaterialTheme.colors.onPrimary,
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