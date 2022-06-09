package com.example.workordie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
//import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
/*import com.example.workordie.ui.CountDownTime.MyApp
import com.example.workordie.ui.accumulateTime.MainApp
import com.example.workordie.ui.accumulateTime.MainViewModel*/
import com.example.workordie.ui.screen.*
import com.example.workordie.ui.theme.WorkOrDieTheme
import android.app.Application
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
//import kotlin.time.ExperimentalTime

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WorkOrDieTheme {
                val viewModelowner = LocalViewModelStoreOwner.current

                viewModelowner?.let {
                    val viewModel: TaskViewModel = viewModel(
                        it,
                        "TaskViewModel",
                        TaskViewModelFactory(
                            LocalContext.current.applicationContext
                                    as Application)
                    )

                    WorkorDieApp(viewModel)
                }
                //WorkorDieApp()
            }
        }
    }
}

//@OptIn(ExperimentalTime::class)
@Composable
fun WorkorDieApp(viewModel: TaskViewModel){
    //State Hoisting
    //use navController to navigate between screens
    val navController = rememberNavController()

    //val viewModel: MainViewModel = viewModel()
    //NavHost holds the NavGraph
    //list the screen you want to navigate below
    NavHost(
        navController = navController,
        startDestination = NavScreen.Home.route
    ){
        composable(route = NavScreen.Home.route){
            Home(navController = navController)
        }
        composable(route = NavScreen.AllTasks.route){
            AllTasks(navController = navController)
        }
        composable(route = NavScreen.AddTask.route){
            AddTask(navController = navController, viewModel = viewModel)
        }
        composable(route = NavScreen.FinishSubmit.route){
            FinishSubmit(navController = navController)
        }
        composable(route = NavScreen.FinishPopup.route){
            FinishPopup(navController = navController)
        }
        composable(route = NavScreen.CountingTime.route){
            CountingTime(navController = navController)
        }
        composable(route = NavScreen.Calendar.route){
            Calendar(navController = navController)
        }
        composable(route = NavScreen.DailyTask.route){
            DailyTask()
        }
        composable(route = NavScreen.Settings.route){
            Setting(navController = navController)
        }
        composable(route = NavScreen.Profile.route){
            Profile(navController = navController)
        }
        /*composable(route = NavScreen.CountdownTime.route){
            MyApp(navController = navController)
        }
        composable(route = NavScreen.AccumulateTime.route){

            com.example.workordie.ui.accumulateTime.MainApp(
                viewModel = viewModel,
                navController = navController
            )
        }*/
    }
}

//viewModel Factory
class TaskViewModelFactory(val application: Application) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TaskViewModel(application) as T
    }
}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    WorkOrDieTheme {
//        WorkorDieApp()
//    }
//}

