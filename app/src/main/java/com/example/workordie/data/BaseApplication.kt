package com.example.workordie.data

import android.app.Application
import com.example.workordie.data.TaskRoomDatabase

class BaseApplication : Application(){
    val database: TaskRoomDatabase by lazy { TaskRoomDatabase.getDatabase(this) }
}