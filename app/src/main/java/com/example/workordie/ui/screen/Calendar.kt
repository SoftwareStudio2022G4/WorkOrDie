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
import java.util.*




// Creating a composable
// function to display Top Bar
@Composable
fun MainContent(navController : NavController) {
    Scaffold(
        topBar = {
            TopAppBar(

                title = {

                    Icon(Icons.Filled.ArrowBack, "backIcon")
                    Spacer(modifier = Modifier.width(240.dp))
                    Text("Calendar", color = Color.Black)
                        },
                backgroundColor = Color(0xFFFBE8A6))
                 },
        content = { MyContent(navController = navController) }
    )
}

// Creating a composable function to
// create two Images and a spacer between them
// Calling this function as content
// in the above function
@Composable
fun MyContent(navController : NavController){

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

    // Declaring DatePickerDialog and setting
    // initial values as current values (present year, month and day)
    val mDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            mDate.value = "$mDayOfMonth/${mMonth+1}/$mYear"
        }, mYear, mMonth, mDay
    )

    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally) {

        // Creating a button that on
        // click displays/shows the DatePickerDialog
        Button(onClick = {
            mDatePickerDialog.show()
        }, colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray) ) {
            Text(text = "Open Date Picker")
        }

        // Adding a space of 100dp height

        // Displaying the mDate value in the Text
        Text(text = "Selected Date: ${mDate.value}", fontSize = 30.sp, textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.size(500.dp))
        /*if(mDate.value == null){
            Text(text = "hello null")
        }
        else if(mDate.value == " "){
            Text(text = "hello blankspace")
        }
        else if(mDate.value == ""){
            Text(text = "hello nothing")
        }
        else{
            Text(text = "so sad")
        }*/
        //Text(text = "test: ${mDate.value}")
        if(mDate.value == ""){
            Button(onClick = {navController.navigate(NavScreen.dailyTask.route)},
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFEFBEF))) {
                Text(text = "Go")
            }
        }
        else{
            Button(onClick = {navController.navigate(NavScreen.dailyTask.route)},
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFBE8A6))) {
                Text(text = "Go")
            }
        }
    }


}

// For displaying preview in
// the Android Studio IDE emulator
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    //MainContent(navController = NavController)
}