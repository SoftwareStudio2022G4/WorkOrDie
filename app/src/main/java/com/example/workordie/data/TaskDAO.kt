package com.example.workordie.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.workordie.model.Task
import java.util.*

@Dao
interface TaskDAO {
    @Insert
    suspend fun insert(task: Task)

    // used to update subtask
    @Update
    suspend fun update(task: Task)

    @Delete
    suspend fun delete(task: Task)

    @Delete
    suspend fun delete(name: String)

    // return value use flow?
    @Query("select * from task_list")
    fun getAllTasks(): LiveData<List<Task>>

    // TODO: get the day's tasks
    @Query("select * from task_list where start_date = :inputDate")
    fun getSingleDayTasks(inputDate: Date) : List<Task>
}