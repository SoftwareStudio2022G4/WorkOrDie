package com.example.workordie.ui.screen

//define each screen as object, we'll use this sealed class when navigating
sealed class NavScreen(val route : String) {
    object Home : NavScreen("home")
    object AddTask : NavScreen("add_task")
    object FinishPopup : NavScreen("finish_popup")
    object FinishSubmit : NavScreen("finish_submit")
    object CountingTime : NavScreen("counting_time")
    object profile : NavScreen("profile")
    object AllTasks : NavScreen("all_tasks")
}