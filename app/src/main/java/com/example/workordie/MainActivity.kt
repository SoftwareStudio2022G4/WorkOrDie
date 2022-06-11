package com.example.workordie


import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.NotificationCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavArgument
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.workordie.ui.CountDownTime.CountdownTimer
import com.example.workordie.ui.accumulateTime.AccumulateTimer
import com.example.workordie.ui.accumulateTime.AccumulateTimeViewModel
import com.example.workordie.ui.screen.*
import com.example.workordie.ui.theme.WorkOrDieTheme
import kotlin.time.ExperimentalTime



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
            }

            val channelId = "notification"

            // 確認是否為Android 8.0以上版本
            // 8.0以上版本才需要建立通知渠道
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                // Create the NotificationChannel
                //設定通知渠道名稱、描述和重要性
                val name = "test"
                val descriptionText = "notify"
                val importance = NotificationManager.IMPORTANCE_DEFAULT
                val mChannel = NotificationChannel(channelId, name, importance)
                mChannel.description = descriptionText
                // Register the channel with the system; you can't change the importance
                // or other notification behaviors after this
                val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.createNotificationChannel(mChannel)
            }

            val builder = NotificationCompat.Builder(this, channelId)
                .setContentTitle("Title")
                .setContentText("Content Text")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

            /*val button = findViewById<Button>(R.id.button)
            button.setOnClickListener {
                with(NotificationManagerCompat.from(this)) {
                    // notificationId is a unique int for each notification that you must define
                    val notificationId = 10
                    notify(notificationId, builder.build())
                }
            }*/
            val currentTimestamp = System.currentTimeMillis()
            //if(currentTimestamp == )
        }




    }
}

@OptIn(ExperimentalTime::class)
@Composable
fun WorkorDieApp(viewModel: TaskViewModel){
    //State Hoisting
    //use navController to navigate between screens
    val navController = rememberNavController()
    val CountTimeviewModel: AccumulateTimeViewModel = viewModel()

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
            AllTasks(navController = navController, viewModel = viewModel)
        }
        composable(route = NavScreen.AddTask.route){
            AddTask(navController = navController, viewModel = viewModel)
        }
        composable(route = NavScreen.SubtaskDetail.route){
            SubtaskDetail(navController = navController)
        }
        composable(
            route = NavScreen.FinishSubmit.route + "/{taskName}/{taskType}",
            arguments = listOf(
                navArgument(name = "taskName"){ NavType.StringType },
                navArgument(name = "taskType"){ NavType.StringType }
            )
        ){ backStackEntry ->
            val taskName = backStackEntry.arguments?.getString("taskName")
            val taskType = backStackEntry.arguments?.getString("taskType")
            FinishSubmit(
                navController = navController,
                taskName = taskName,
                taskType = taskType
            )
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
            DailyTask(navController = navController)
        }
        composable(route = NavScreen.Settings.route){
            Setting(navController = navController)
        }
        composable(route = NavScreen.Profile.route){
            Profile(navController = navController)
        }
        composable(route = NavScreen.CountdownTime.route){
            CountdownTimer(navController = navController)
        }
        composable(route = NavScreen.AccumulateTime.route){
            AccumulateTimer(
                viewModel = CountTimeviewModel,
                navController = navController
            )
        }
    }
}

//viewModel Factory
class TaskViewModelFactory(val application: Application) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TaskViewModel(application) as T
    }
}

/*@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WorkOrDieTheme {
        WorkorDieApp()
    }
}*/