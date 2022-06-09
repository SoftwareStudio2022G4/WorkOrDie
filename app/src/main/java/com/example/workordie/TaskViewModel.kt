package com.example.workordie

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.workordie.data.TaskRepository
import com.example.workordie.data.TaskRoomDatabase
import com.example.workordie.model.Task
import java.util.*

class TaskViewModel(application: Application) : ViewModel() {
    val allProducts: LiveData<List<Task>>
    private val repository: TaskRepository
    val searchResults: MutableLiveData<List<Task>>

    init {
        val taskDb = TaskRoomDatabase.getDatabase(application)
        val taskDao = taskDb.taskDAO()
        repository = TaskRepository(taskDao)

        allProducts = repository.allTasks
        searchResults = repository.searchResults
    }

    fun insert(task: Task) {
        repository.insertTask(task)
    }

    fun findSingleDayTask(date: String) {
        repository.findSingleDayTasks(date)
    }

    fun deleteTask(task: Task) {
        repository.deleteTask(task)
    }
}

