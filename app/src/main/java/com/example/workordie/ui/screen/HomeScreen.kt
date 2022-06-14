package com.example.workordie.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.workordie.ui.theme.Blue900
import com.example.graphql.apolloClient
import com.example.rocketreserver.*
import com.example.rocketreserver.type.CLASSTYPE
import com.example.workordie.TaskViewModel
import com.example.workordie.model.Task
import com.example.workordie.ui.theme.WorkOrDieTheme
import com.example.workordie.ui.theme.Yellow100
import com.example.workordie.ui.theme.Yellow50
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/* Scaffold reference link:
https://www.jetpackcompose.net/scaffold
https://www.youtube.com/watch?v=UDW4v88V41M
* */

/* TODO
(1) Cant find suitable icon for All Tasks
(2) The onclick of bottom bar item should be navigation in the future
body content
(3) Separate lines in table (optional)
(4) LazyColumn in function TaskTable to be fixed
* */

@Composable
fun Home(navController : NavController, viewModel: TaskViewModel){
    val scaffoldState : ScaffoldState = rememberScaffoldState(/*rememberDrawerState(DrawerValue.Closed)*/)
    val scope : CoroutineScope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            HomeTopBar(scaffoldState = scaffoldState, scope = scope, navController)
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(NavScreen.AddTask.route) }
            ){
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "fab icon"
                )
            }
        },
        drawerContent = {
            DrawerContent()
        },
        content = {
            BodyContent(navController, viewModel)
        },
        bottomBar = {
            HomeBottomBar(navController)
        }
    )
}

@Composable
fun HomeTopBar(scaffoldState : ScaffoldState, scope : CoroutineScope, navController : NavController){
    val drawerState = scaffoldState.drawerState

    TopAppBar(
        title = { },
        modifier = Modifier.fillMaxWidth(),
        navigationIcon = {
            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.End
            ){
                //menu
                IconButton(
                    onClick = {
                        navController.navigate(NavScreen.Settings.route)
                    }
                ) {
                    Icon(
                        Icons.Default.Settings,
                        contentDescription = "Settings"
                    )
                }
                //profile
                IconButton(
                    onClick = {navController.navigate(NavScreen.GoogleSignIn.route) }
                ) {
                    Icon(
                        Icons.Default.Person,
                        contentDescription = "Profile"
                    )
                }
            }
        }
    )
}

suspend fun ApolloBodyTest() {
    val input = CLASSTYPE.Probability
    apolloClient.mutation(DeleteHomeworkMutation(type = input, index = 1)).execute()
    apolloClient.mutation(DeleteHomeworkMutation(type = input, index = 0)).execute()
    val response = apolloClient.query(ClassDetailQuery(type = input)).execute()
    Log.d("LaunchList", "Success ${response.data}")
}

fun CompareDate(task: Task): Boolean {
    //today
    val date = Date()
    val dateformat = SimpleDateFormat("yyyy/MM/dd")
    val dateString = dateformat.format(date)

    try {
        val startDate: Date = dateformat.parse(task.startDate)
        val endDate: Date = dateformat.parse(task.endDate)

        //tomorrow
        val c: Calendar = Calendar.getInstance()
        c.setTime(endDate)
        c.add(Calendar.DATE, 1)
        val theDayAfterEndDate = c.getTime()

        Log.d("ABCD", "startDate = ${task.startDate}")
        Log.d("ABCD", "endDate = ${task.endDate}")
        Log.d("ABCD", "today = ${dateString}")
        Log.d("ABCD", "theDayAfterEndDate = ${theDayAfterEndDate}")
        return date == startDate || (date.after(startDate) && date.before(theDayAfterEndDate)) //compare endDate with date+1
    } catch (e: ParseException) {
        return false
    }
    return false
}

@Composable
fun BodyContent(navController : NavController, viewModel: TaskViewModel){
    val date = Date()
    val formatterMonth = SimpleDateFormat("MMM")
    val formatterDay = SimpleDateFormat("dd")
    val monthString: String = formatterMonth.format(date)
    val dayString: String = formatterDay.format(date)
    val allTaskList by viewModel.allProducts.observeAsState()
    val testtask by viewModel.searchResults.observeAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = monthString,
                color = MaterialTheme.colors.onSurface,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = dayString,
                color = MaterialTheme.colors.onSurface,
                fontWeight = FontWeight.Bold,
                fontSize = 48.sp
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        TaskTable() // Today's task table*
        Spacer(modifier = Modifier.height(24.dp))
        Summary() // Summary table
    }
}

@Composable
private fun TaskTable(names: List<String> = List(1) { "$it" }) {
    LazyColumn {
        items(items = names) {name ->
            Task()
        }
    }
}

@Composable
private fun Task() {
    val backgroundShape = RoundedCornerShape(6.dp)
    Card(
        backgroundColor = Yellow100,
        shape = RoundedCornerShape(6.dp),
        modifier = Modifier
            .padding(vertical = 1.dp, horizontal = 1.dp)
            .size(width = 340.dp, height = 180.dp)
            .shadow(12.dp, backgroundShape)
    ) {
        Column(
            horizontalAlignment = Alignment.Start
        ) {
            Spacer(modifier = Modifier.height(6.dp))
            Row() {
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "Today's Task:",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Left,
                    color = Blue900,
                    modifier = Modifier.padding(bottom = 6.dp)
                )
            }
            Card(
                backgroundColor = Yellow50,
                modifier = Modifier
                    .size(width = 340.dp, height = 180.dp)
            ){}
        }
        TaskContent()
    }
}

@Composable
private fun TaskContent() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(36.dp))
        for(i in 1..3){
            Row(
                horizontalArrangement = Arrangement.spacedBy(30.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                if(CompareDate(task)) {
                  Text(text = "${task.taskName}",
                      fontSize = 18.sp,
                      color = Blue900)
                  Spacer(modifier = Modifier.width(6.dp))
                  Text(text = "${task.totalTimeSpent} sec",
                      fontSize = 18.sp,
                      color = Blue900)
                  IconButton(onClick = { navController.navigate(NavScreen.SubtaskDetail.route}) {
                      Icon(
                          Icons.Default.Menu,
                          contentDescription = "Menu",
                          modifier = Modifier.size(ButtonDefaults.IconSize)
                      )
                  }
                  IconButton(onClick = {
                      Log.d("ABCD", "taskId = ${task.id}")
                      navController.navigate(NavScreen.CountingTime.route + "/${task.id}") 
                  }){
                      Icon(
                          Icons.Default.PlayArrow,
                          contentDescription = "PlayButton",
                          modifier = Modifier.size(ButtonDefaults.IconSize)
                      )
                  } // iconbutton
                } // if CompareDate
            } // end Row
        }
    }
}

@Composable
private fun Summary() {
    val backgroundShape = RoundedCornerShape(6.dp)
    Card(
        backgroundColor = Yellow100,
        shape = RoundedCornerShape(6.dp),
        modifier = Modifier
            .padding(vertical = 1.dp, horizontal = 1.dp)
            .size(width = 340.dp, height = 90.dp)
            .shadow(12.dp, backgroundShape)
    ){
        Column(
            horizontalAlignment = Alignment.Start
        ) {
            Row() {
                Spacer(modifier = Modifier.width(12.dp))
                Text(text = "Finished Tasks",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Blue900,
                    modifier = Modifier.padding(top = 6.dp, bottom = 6.dp)
                )
            }
            Card(
                backgroundColor = Yellow50,
                modifier = Modifier
                    .size(width = 340.dp, height = 48.dp)
            ){}

        }
        Column(
            horizontalAlignment = Alignment.End
        ) {
            Row() {
                Text(text = "Time spent",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Blue900,
                    modifier = Modifier.padding(top = 6.dp, bottom = 6.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
            }

        }
        SummaryContent()
    }
}

@Composable
private fun SummaryContent() {
    Column(
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(48.dp))
        Row() {
            Spacer(modifier = Modifier.width(48.dp))
            Text(text = "Task 0",
                fontSize = 20.sp,
                color = Blue900,
                modifier = Modifier.padding(bottom = 6.dp)
            )
        }
        Spacer(modifier = Modifier.height(100.dp))
    }
    Column(
        horizontalAlignment = Alignment.End
    ) {
        Spacer(modifier = Modifier.height(48.dp))
        Row() {
            Text(text = "3 hr",
                fontSize = 20.sp,
                color = Blue900,
                modifier = Modifier.padding(bottom = 6.dp)
            )
            Spacer(modifier = Modifier.width(48.dp))
        }

    }
}

@Composable
fun DrawerContent(){
    Text(text = "Drawer Menu 1")
}

//pass navcontroller here after creating the following 3 pages
@Composable
fun HomeBottomBar(navController : NavController){
    val selectedIndex = remember { mutableStateOf(1) }
    BottomNavigation(
        elevation = 10.dp
    ) {
        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Edit ,
                    contentDescription = "All Tasks"
                )
            },
            label = {
                Text(text = "All Tasks")
            },
            selected = (selectedIndex.value == 0),
            onClick = {
                selectedIndex.value = 0
                navController.navigate(NavScreen.AllTasks.route)
            }
        )

        BottomNavigationItem(
            icon = {
                Icon(imageVector = Icons.Default.CheckCircle,
                    contentDescription = "Today's Task"
                )
            },
            label = {
                Text(text = "Today's Task")
            },
            selected = (selectedIndex.value == 1),
            onClick = {
                selectedIndex.value = 1
                navController.navigate(NavScreen.Home.route)
            }
        )

        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "Calendar"
                )
            },
            label = {
                Text(text = "Calendar")
            },
            selected = (selectedIndex.value == 2),
            onClick = {
                selectedIndex.value = 2
                navController.navigate(NavScreen.Calendar.route)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    WorkOrDieTheme {
        Home(rememberNavController())
    }
}

