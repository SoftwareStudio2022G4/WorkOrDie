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
import com.example.workordie.ui.theme.Blue900
import com.example.workordie.ui.theme.WorkOrDieTheme
import com.example.workordie.ui.theme.Yellow100
import com.example.workordie.ui.theme.Yellow50
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
            HomeTopBar(scaffoldState = scaffoldState, scope = scope)
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
            HomeBottomBar()
        }
    )
}

@Composable
fun HomeTopBar(scaffoldState : ScaffoldState, scope : CoroutineScope){
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

                //profile
                IconButton(
                    onClick = { }
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
    val formatterMonth = SimpleDateFormat("MMM")
    val formatterDay = SimpleDateFormat("dd")
    val monthString: String = formatterMonth.format(date)
    val dayString: String = formatterDay.format(date)

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
        /*Today's task table*/
        TaskTable()
        Spacer(modifier = Modifier.height(48.dp))
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
                navController.navigate(NavScreen.CountingTime.route)
            }
        ) {
            Text(text = "test for Counting")
        }
    }
}

@Composable
private fun TaskTable(names: List<String> = List(1) { "$it" }) {
    LazyColumn {
        items(items = names) {name ->
            Task(name = name)
        }
    }
}

@Composable
private fun Task(name: String) {
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
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Today's Task:",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Left,
                color = Blue900,
                modifier = Modifier.padding(bottom = 6.dp)
            )
            Card(
                backgroundColor = Yellow50,
                modifier = Modifier
                    .size(width = 340.dp, height = 180.dp)
                    //.padding(top = 24.dp)
            ){}
        }
        TaskContent(name)
    }
}

@Composable
private fun TaskContent(name: String) {
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
                Text(text = "Task $i",
                    fontSize = 18.sp,
                    color = Blue900)
                Spacer(modifier = Modifier.width(6.dp))
                Text(text = "$i h",
                    fontSize = 18.sp,
                    color = Blue900)
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        Icons.Default.Menu,
                        contentDescription = "Menu",
                        modifier = Modifier.size(ButtonDefaults.IconSize)
                    )
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        Icons.Default.PlayArrow,
                        contentDescription = "PlayButton",
                        modifier = Modifier.size(ButtonDefaults.IconSize)
                    )
                }
            }
        }
    }

}
@Composable
fun DrawerContent(){
    Text(text = "Drawer Menu 1")
}

//pass navcontroller here after creating the following 3 pages
@Composable
fun HomeBottomBar(){
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



@Preview(showBackground = true)
@Composable
fun HomePreview() {
    WorkOrDieTheme {
        Home(rememberNavController())
    }
}