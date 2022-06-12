package com.example.workordie.ui.screen

import android.app.DatePickerDialog
import android.icu.util.ULocale
import android.os.Bundle
import android.widget.DatePicker
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import java.text.SimpleDateFormat
import java.util.*




// Creating a composable
// function to display Top Bar
@Composable
fun Calendar(navController : NavController) {
    val scaffoldState : ScaffoldState = rememberScaffoldState(/*rememberDrawerState(DrawerValue.Closed)*/)
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {
                    Spacer(modifier = Modifier.width(240.dp))
                    Text(text = "Calendar")
                },
            )
        },
        content = { CalendarContent(navController = navController) },
        bottomBar = {
            CalendarBottomBar(navController)
        }
    )
}

// Creating a composable function to
// create two Images and a spacer between them
// Calling this function as content
// in the above function
@Composable
fun CalendarContent(navController : NavController){

    // Fetching the Local Context
    val mContext = LocalContext.current

    // Declaring integer values
    // for year, month and day
    val mYear: Int
    val mMonth: Int
    val mDay: Int

    // Initializing a Calendar
    val mCalendar = Calendar.getInstance()

    // Fetching current year, month and day
    mYear = mCalendar.get(Calendar.YEAR)
    mMonth = mCalendar.get(Calendar.MONTH)
    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)

    mCalendar.time = Date()

    // Declaring a string value to
    // store date in string format
    val mDate = remember { mutableStateOf("") }

    // date format for navigation
    val dateFormat = SimpleDateFormat("yyyy-MM-dd") // NOTE: use '/' app will crash

    // Declaring DatePickerDialog and setting
    // initial values as current values (present year, month and day)
    val mDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            mDate.value = "$mYear/${mMonth+1}/$mDayOfMonth"
        }, mYear, mMonth, mDay
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Creating a button that on
        // click displays/shows the DatePickerDialog
        Button(
            onClick = {
                mDatePickerDialog.show()
            }
        ) {
            Text(text = "Open Date Picker")
        }

        // Displaying the mDate value in the Text
        Text(
            text = "Selected Date: ${mDate.value}",
            fontSize = 30.sp,
            textAlign = TextAlign.Center
        )

        // Adding a space of 500dp height
        Spacer(modifier = Modifier.size(500.dp))

        if(mDate.value == ""){
            Button(
                onClick = {
                    val date = Date()
                    val dateString = dateFormat.format(date)
                    navController.navigate(NavScreen.DailyTask.route + "/$dateString")
                },
            ) {
                Text(text = "Go")
            }
        }
        else{
            Button(
                onClick = {
                    val badDateFormat = SimpleDateFormat("yyyy/MM/dd")
                    val date = badDateFormat.parse(mDate.value)
                    val dateString = dateFormat.format(date)
                    navController.navigate(NavScreen.DailyTask.route + "/${dateString}")
                }
            ) {
                Text(text = "Go")
            }
        }
    }
}
@Composable
fun CalendarBottomBar(navController : NavController){
    val selectedIndex = remember { mutableStateOf(2) }
    BottomNavigation(
        elevation = 10.dp
    ) {
        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Edit,
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
                Icon(
                    imageVector = Icons.Default.CheckCircle,
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
// For displaying preview in
// the Android Studio IDE emulator
@Preview(showBackground = true)
@Composable
fun CalendarPreview() {
    //MainContent(navController = NavController)
    Calendar(rememberNavController())
}