package com.example.workordie.ui.screen


import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

/* Scaffold reference link:
https://www.jetpackcompose.net/scaffold
https://www.youtube.com/watch?v=UDW4v88V41M
* */

/* TODOs
cant find suitable icon for All Tasks
the onclick of bottom bar item should be navigation in the future
havent place the profile icon
body content
* */

@Composable
fun Home(navController : NavController){
    val scaffoldState : ScaffoldState = rememberScaffoldState(/*rememberDrawerState(DrawerValue.Closed)*/)
    val scope : CoroutineScope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            MyTopBar(scaffoldState = scaffoldState, scope = scope)
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
            BodyContent()
        },
        bottomBar = {
            MyBottomBar()
        }
    )
}

@Composable
fun MyTopBar(scaffoldState : ScaffoldState, scope : CoroutineScope){
    val drawerState = scaffoldState.drawerState

    TopAppBar(
        title = { },
        navigationIcon = {
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
                    Icons.Default.Menu,
                    contentDescription = "Menu"
                )
            }
        }
    )
}

@Composable
fun BodyContent(){
    val date = Date()
    val formatter = SimpleDateFormat("MMM dd")
    val dateString: String = formatter.format(date)
    Text(
        text = dateString,
        fontSize = 36.sp
    )
}

@Composable
fun DrawerContent(){
    Text(text = "Drawer Menu 1")
}

//pass navcontroller here after creating the following 3 pages
@Composable
fun MyBottomBar(){
    val selectedIndex = remember { mutableStateOf(0) }
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
            }
        )
    }
}


//Don't uncomment here, since Home() needs a parameter
/*@Preview(showBackground = true)
@Composable
fun HomePreview() {
    WorkOrDieTheme {
        Home()
    }
}*/