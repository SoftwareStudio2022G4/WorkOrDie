package com.example.workordie.ui.screen


import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.workordie.ui.theme.WorkOrDieTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
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
fun Home(navController : NavController){
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
            BodyContent(navController)
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

@Composable
fun BodyContent(navController : NavController){
    val date = Date()
    val formatter = SimpleDateFormat("MMM dd")
    val dateString: String = formatter.format(date)

    
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
        for(i in 1..3){
            Row(
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(text = "Task $i")
                Text(text = "$i h")
                IconButton(onClick = { navController.navigate(NavScreen.SubtaskDetail.route)}) {
                    Icon(
                        Icons.Default.Menu,
                        contentDescription = "Menu"
                    )
                }
                IconButton(onClick = { navController.navigate(NavScreen.CountingTime.route) }) {
                    Icon(
                        Icons.Default.PlayArrow,
                        contentDescription = "PlayButton"
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        Column() {
            Row(
                horizontalArrangement = Arrangement.spacedBy(40.dp)
            ) {
                Text(text = "Finished Tasks")
                Text(text = "Time spent")
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(140.dp)
            ) {
                Text(
                    text = "Task 0",
                    textAlign = TextAlign.Left
                )

                Text(
                    text = "3h",
                    textAlign = TextAlign.Right
                )
            }
        }

        //testing only
        Button(
            onClick = {
                navController.navigate(NavScreen.Settings.route)
            }
        ) {
            Text(text = "test for Counting")
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