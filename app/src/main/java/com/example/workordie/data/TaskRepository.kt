package com.example.workordie.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.workordie.model.Task
import kotlinx.coroutines.*
import java.util.*

class TaskRepository(private val taskDao: TaskDAO) {
    val taskResults = MutableLiveData<List<Task>>()

    val allTasks: LiveData<List<Task>> = taskDao.getAllTasks()
    val searchResults = MutableLiveData<List<Task>>()

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun insertTask(task: Task){
        coroutineScope.launch(Dispatchers.IO) {
            taskDao.insert(task)
        }
    }

    fun deleteTask(task: Task) {
        coroutineScope.launch(Dispatchers.IO) {
            taskDao.delete(task)
        }
    }

    fun findSingleDayTasks(date: String) {
        coroutineScope.launch(Dispatchers.Main) {
            taskResults.value = asyncFindSingleDay(date).await()
        }
    }

    private fun asyncFindSingleDay(date: String): Deferred<List<Task>?> =
        coroutineScope.async(Dispatchers.IO) {
            return@async taskDao.getSingleDayTasks(date)
        }
}