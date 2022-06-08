package com.example.workordie.data

import androidx.room.*
import com.example.workordie.model.Task

@Dao
interface TaskDAO {
    @Insert
    suspend fun insert(task: Task)

    // used to update subtask
    @Update
    suspend fun update(task: Task)

    @Delete
    suspend fun delete(task: Task)

    // return value use flow?
    @Query("select * from task_list")
    fun getAllTasks() : List<Task>

    // TODO: get the day's tasks
}