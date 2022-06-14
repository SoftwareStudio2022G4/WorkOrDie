package com.example.workordie.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.apollographql.apollo3.api.Optional
import com.example.graphql.apolloClient
import com.example.rocketreserver.AddToHomeworkMutation
import com.example.rocketreserver.CreateHomeworkMutation
import com.example.rocketreserver.GetHomeworkQuery
import com.example.rocketreserver.type.CLASSTYPE
import com.example.workordie.TaskViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/* TODO
the info box between subtasks and done button
* */

//val taskTypes: MutableList<String> =  mutableListOf<String>()
//private fun inputTypes() {
//    taskTypes.add("Probability")
//    taskTypes.add("Computer_Architecture")
//    taskTypes.add("Software_Studio")
//}

private fun determineClassType(type: String): CLASSTYPE {
    val classtype: CLASSTYPE
    if (type == "Software_Studio") {
        classtype = CLASSTYPE.Software_Studio
    }
    else if (type == "Computer_Architecture") {
        classtype = CLASSTYPE.Computer_Architecture
    }
    else if (type == "Probability") {
        classtype = CLASSTYPE.Probability
    }
    else {
        classtype = CLASSTYPE.UNKNOWN__
    }
    return classtype
}

@Composable
fun FinishPopup(
    navController : NavController,
    viewModel: TaskViewModel,
    pickedId: String?
){
    val scaffoldState : ScaffoldState = rememberScaffoldState(/*rememberDrawerState(DrawerValue.Closed)*/)

    Scaffold(scaffoldState = scaffoldState) {
        Column {
            FinishPopupBody(
                navController = navController,
                viewModel = viewModel,
                pickedId = pickedId
            )
        }
    }
}
@Composable
fun FinishPopupBody(
    navController : NavController,
    viewModel: TaskViewModel,
    pickedId: String?
){
    val tasklist by viewModel.searchResults.observeAsState()
    viewModel.findSingleDayTask(pickedId!!.toInt())
    val task = tasklist?.get(0)!!

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 100.dp),
        verticalArrangement = Arrangement.spacedBy(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Hooray!",
            fontSize = 30.sp
        )

        Text(
            text = "You've finished"
        )

        Text(
            text = "2 subtasks!"
        )

        for (i in 1..4){
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                //only check the first two
                //IMPORTANT:this is only for ui purpose
                Checkbox(
                    checked = (i<=2),
                    onCheckedChange = null
                )
                Text(
                    text = "subtask $i"
                )
            }
        }

        //TODO: info box here

        //will handle onclick event after finish designing the counting time screen
        Button(
            onClick = {
//                val classtype = determineClassType(task.taskType)
//                runBlocking{
//                        launch{
//                            if (apolloClient.query(GetHomeworkQuery(index = task.index, type = classtype)).execute() == null ) {
//                                apolloClient.mutation(CreateHomeworkMutation(index = task.index, type = classtype)).execute()
//                            }
//                            apolloClient.mutation(AddToHomeworkMutation(
//                                index = task.index,
//                                type = classtype
//                            )).execute()
//                        }
//                }
                viewModel.deleteSingleTaskTest(pickedId!!.toInt())
                navController.navigate(NavScreen.Home.route)
            }
        ) {
            Text(text = "Done")
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Done"
            )
        }
    }
}
