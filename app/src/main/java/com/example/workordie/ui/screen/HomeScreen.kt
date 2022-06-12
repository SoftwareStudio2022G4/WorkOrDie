package com.example.workordie.ui.screen


import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.graphql.apolloClient
import com.example.rocketreserver.*
import com.example.rocketreserver.type.CLASSTYPE
import com.example.workordie.TaskViewModel
import com.example.workordie.model.Task
import com.example.workordie.ui.theme.WorkOrDieTheme
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
cant find suitable icon for All Tasks
the onclick of bottom bar item should be navigation in the future
body content
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
                        /*scope.launch {
                            if(drawerState.isClosed){
                                drawerState.open()
                            }else{
                                drawerState.close()
                            }
                        }*/
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
                    onClick = {navController.navigate(NavScreen.Profile.route) }
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
    val formatter = SimpleDateFormat("MMM dd")
    val dateString: String = formatter.format(date)

    val allTaskList by viewModel.allProducts.observeAsState()

    // test
    val testtask by viewModel.searchResults.observeAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = dateString,
            modifier = Modifier,
            fontSize = 36.sp,
            textAlign = TextAlign.Center
        )


        Text(
            text = "Today's Task:",
            fontSize = 30.sp,
            textAlign = TextAlign.Right
        )
        allTaskList?.forEach{ task ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                if(CompareDate(task)) {
                    Text(text = "${task.taskName}")
                    Text(text = "${task.totalTimeSpent} h")
                    IconButton(onClick = { navController.navigate(NavScreen.SubtaskDetail.route)}) {
                        Icon(
                            Icons.Default.Menu,
                            contentDescription = "Menu"
                        )
                    }
                    IconButton(onClick = {
                        Log.d("ABCD", "taskId = ${task.id}")
                        navController.navigate(NavScreen.CountingTime.route + "/${task.id}") }) {
                        Icon(
                            Icons.Default.PlayArrow,
                            contentDescription = "PlayButton"
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

//        Column() {
//            Row(
//                horizontalArrangement = Arrangement.spacedBy(40.dp)
//            ) {
//                Text(text = "Finished Tasks")
//                Text(text = "Time spent")
//            }
//            Row(
//                horizontalArrangement = Arrangement.spacedBy(140.dp)
//            ) {
//                Text(
//                    text = "Task 0",
//                    textAlign = TextAlign.Left
//                )
//
//                Text(
//                    text = "3h",
//                    textAlign = TextAlign.Right
//                )
//            }
//        }

        //testing only
//        Column() {
//            runBlocking{
//                launch{
//                    ApolloBodyTest()
//                }
//            }
//        }
        Button(
            onClick = {
                viewModel.findSingleDayTask(1)
                testtask?.forEach{ item ->
                    Log.d("ABCD", "${item.taskName}")
                }
            }
        ) {
            Text(text = "delete test")
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



//@Preview(showBackground = true)
//@Composable
//fun HomePreview() {
//    WorkOrDieTheme {
//        Home(rememberNavController())
//    }
//}