package com.example.workordie.ui.screen

import android.app.TimePickerDialog
import android.view.LayoutInflater
import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.workordie.ui.theme.WorkOrDieTheme
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.RadioButton
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.workordie.R
import com.example.workordie.ui.screen.Calendar
import java.util.*

@Composable
fun Setting(modifier : Modifier = Modifier,navController : NavController){
    androidx.compose.material.Surface(color = MaterialTheme.colors.background)
    {
        Scaffold() {
            Column  {

                TopAppBar(
                    title = { },
                    navigationIcon = {
                        IconButton(onClick = {navController.navigate(NavScreen.Home.route)}) {
                            Icon(Icons.Filled.ArrowBack, "backIcon")

                        }
                    },


                    )
                Card(){
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Column() {
                            Text(text = "check")
                            Text(text = "achievement")
                        }

                        // Fetching local context
                        val mContext = LocalContext.current

                        // Declaring and initializing a calendar
                        val mCalendar = Calendar.getInstance()
                        val mHour = mCalendar[Calendar.HOUR_OF_DAY]
                        val mMinute = mCalendar[Calendar.MINUTE]

                        // Value for storing time as a string
                        val mTime = remember { mutableStateOf("") }

                        // Creating a TimePicker dialod
                        val mTimePickerDialog = TimePickerDialog(
                            mContext,
                            {_, mHour : Int, mMinute: Int ->
                                mTime.value = "$mHour:$mMinute"
                            }, mHour, mMinute, false
                        )
                        Spacer(modifier = Modifier.width(60.dp))
                        // Display selected time
                        Text(text = "Selected Time:")
                        Text(text = " ${mTime.value}")
                        Button(onClick = { mTimePickerDialog.show()}) {
                            Text(text = "Time Picker", color = Color.Black)
                        }

                        // Add a spacer of 100dp





                    }
                }
                //divider can change color directly
                Divider(thickness = 5.dp)
                Card(modifier = Modifier.fillMaxWidth()){
                    Row() {
                        Column() {
                            Text(text = "dark mode")

                        }
                        Spacer(modifier = Modifier.width(258.dp))
                        val checkedState = remember { mutableStateOf(true) }
                        Switch(
                            checked = checkedState.value,
                            onCheckedChange = { checkedState.value = it },
                            colors = SwitchDefaults.colors(Color.Gray)
                        )



                    }
                }
                Divider(thickness = 5.dp)
                Card(modifier = Modifier.fillMaxWidth()){
                    Row() {
                        Column() {
                                val checkedState = remember { mutableStateOf(false) }
                                Row() {
                                    Text(text = "Notification")
                                    Spacer(modifier = Modifier.width(250.dp))

                                    Switch(
                                        checked = checkedState.value,
                                        onCheckedChange = { checkedState.value = it },
                                        colors = SwitchDefaults.colors(Color.Gray)

                                    )
                                }

                            if (checkedState.value == true) {
                                val radioOptions =
                                    listOf("default notification", "Customized notification")
                                val (selectedOption, onOptionSelected) = remember {
                                    mutableStateOf(
                                        radioOptions[0]
                                    )
                                }
                                // Note that Modifier.selectableGroup() is essential to ensure correct accessibility behavior
                                Column(Modifier.selectableGroup()) {
                                    radioOptions.forEach { text ->
                                        Row(
                                            Modifier
                                                .fillMaxWidth()
                                                .height(56.dp)
                                                .selectable(
                                                    selected = (text == selectedOption),
                                                    onClick = { onOptionSelected(text) },
                                                    role = Role.RadioButton
                                                )
                                                .padding(horizontal = 16.dp),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            RadioButton(
                                                selected = (text == selectedOption),
                                                onClick = null // null recommended for accessibility with screenreaders
                                            )
                                            Text(
                                                text = text,
                                                style = MaterialTheme.typography.body1.merge(),
                                                modifier = Modifier.padding(start = 16.dp)
                                            )
                                        }
                                    }
                                }

                            }
                        }




                    }
                }
                Divider(thickness = 5.dp)

                var ShowFrequency = remember { mutableStateOf(false) }
                Card(modifier = Modifier.fillMaxWidth()) {

                    Column() {
                        Row() {
                            Text(text = "Frequency")
                            Spacer(modifier = Modifier.width(200.dp))
                            Button(onClick = {ShowFrequency.value = !ShowFrequency.value}) {
                                if(ShowFrequency.value == false){
                                    Text(text = "show more")
                                }
                                else{
                                    Text(text = "show less")

                                }

                            }
                        }
                        if(ShowFrequency.value == true){
                            Column() {

                                val radioOptions2 =
                                    listOf("Everyday", "Every Two Days","Every Four Days", "Once a week")
                                val (selectedOption, onOptionSelected) = remember {
                                    mutableStateOf(
                                        radioOptions2[0]
                                    )
                                }
                                // Note that Modifier.selectableGroup() is essential to ensure correct accessibility behavior
                                Column(Modifier.selectableGroup()) {
                                    radioOptions2.forEach { text ->
                                        Row(
                                            Modifier
                                                .fillMaxWidth()
                                                .height(28.dp)
                                                .selectable(
                                                    selected = (text == selectedOption),
                                                    onClick = { onOptionSelected(text) },
                                                    role = Role.RadioButton
                                                )
                                                .padding(horizontal = 16.dp),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            RadioButton(
                                                selected = (text == selectedOption),
                                                onClick = null // null recommended for accessibility with screenreaders
                                            )
                                            Text(
                                                text = text,
                                                style = MaterialTheme.typography.body1.merge(),
                                                modifier = Modifier.padding(start = 16.dp)
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }



                }
                Divider(thickness = 5.dp)
                var ShowReminders = remember { mutableStateOf(false) }
                Card(modifier = Modifier.fillMaxWidth()) {

                    Column() {
                        Row() {
                            Text(text = "Reminders")
                            Spacer(modifier = Modifier.width(200.dp))
                            Button(onClick = {ShowReminders.value = !ShowReminders.value}) {
                                if(ShowReminders.value == false){
                                    Text(text = "show more")
                                }
                                else{
                                    Text(text = "show less")

                                }

                            }
                        }
                        if(ShowReminders.value == true){
                            Column() {

                                val radioOptions2 =
                                    listOf("8:00", "12:00","16:00","20:00")
                                val (selectedOption, onOptionSelected) = remember {
                                    mutableStateOf(
                                        radioOptions2[0]
                                    )
                                }
                                // Note that Modifier.selectableGroup() is essential to ensure correct accessibility behavior
                                Column(Modifier.selectableGroup()) {
                                    radioOptions2.forEach { text ->
                                        Row(
                                            Modifier
                                                .fillMaxWidth()
                                                .height(28.dp)
                                                .selectable(
                                                    selected = (text == selectedOption),
                                                    onClick = { onOptionSelected(text) },
                                                    role = Role.RadioButton
                                                )
                                                .padding(horizontal = 16.dp),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            RadioButton(
                                                selected = (text == selectedOption),
                                                onClick = null // null recommended for accessibility with screenreaders
                                            )
                                            Text(
                                                text = text,
                                                style = MaterialTheme.typography.body1.merge(),
                                                modifier = Modifier.padding(start = 16.dp)
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }



                }
                Divider(thickness = 5.dp)
                Card(modifier = Modifier.height(50.dp).fillMaxWidth()) {
                    TextField(
                        placeholder = { Text(text = "Message")},
                        value = "",
                        onValueChange = {},
                        modifier = modifier.width(390.dp)
                    )

                }





            }
        }
    }


}


@Preview(showBackground = true)
@Composable
fun SettingPreview() {
    WorkOrDieTheme {
        //Setting(rememberNavController(navigators = ))
        Setting(modifier = Modifier, rememberNavController())

    }
}