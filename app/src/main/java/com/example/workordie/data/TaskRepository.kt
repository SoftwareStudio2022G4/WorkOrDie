package com.example.workordie.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.workordie.model.Task
import kotlinx.coroutines.*
import java.util.*

class TaskRepository(private val taskDao: TaskDAO) {
    val taskResults = MutableLiveData<List<Task>>()

    val allTasks: LiveData<List<Task>> = taskDao.getAllTasks()
    var searchResults = MutableLiveData<List<Task>>()

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun insertTask(task: Task){
        coroutineScope.launch(Dispatchers.IO) {
            taskDao.insert(task)
        }
    }

    fun updateTaskTimeSpent(timeSpent: Long, inputId: Int){
        coroutineScope.launch(Dispatchers.IO) {
            taskDao.updateTimeSpent(timeSpent, inputId)
        }
    }

    fun deleteTask(task: Task) {
        coroutineScope.launch(Dispatchers.IO) {
            taskDao.delete(task)
        }
    }

    fun deleteSingleTaskTest() {
        coroutineScope.launch(Dispatchers.IO) {
            taskDao.deleteSingle()
        }
    }

    fun findSingleDayTasks(id: Int) {
        coroutineScope.launch(Dispatchers.Main) {
            searchResults.value = asyncFindSingleDayTasks(id).await()
        }
    }



    private fun asyncFindSingleDayTasks(id: Int): Deferred<List<Task>?> =
        coroutineScope.async(Dispatchers.IO) {
            return@async taskDao.getSingleDayTasks(id)
        }
}