package com.example.workordie.data

import androidx.room.*
import com.example.workordie.model.SubTask
import com.example.workordie.model.Task

@Dao
interface SubTaskDAO {
    @Insert
    suspend fun insert(subTask: SubTask)

    // used to update subtask
    @Update
    suspend fun update(subTask: SubTask)

    @Delete
    suspend fun delete(subTask: SubTask)

    // return value use flow?
    @Query("select * from subtask_list")
    fun getAllSubTasks() : List<SubTask>
}