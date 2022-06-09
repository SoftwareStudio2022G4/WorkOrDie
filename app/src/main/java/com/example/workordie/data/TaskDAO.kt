package com.example.workordie.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.workordie.model.SubTask
import com.example.workordie.model.Task
import com.example.workordie.model.TaskWithSubTasks
import java.util.*

@Dao
interface TaskDAO {

    @Insert
    suspend fun insert(task: Task)

    @Insert
    suspend fun insertSubTask(subTask: SubTask)

    // used to update subtask
    @Update
    suspend fun update(task: Task)

    @Delete
    suspend fun delete(task: Task)

    // return value use flow?
    @Query("select * from task_list")
    fun getAllTasks(): LiveData<List<Task>>

    @Query("select * from task_list where start_date = :inputDate")
    fun getSingleDayTasks(inputDate: String) : List<Task>

    @Query("select * from task_list inner join subtask_list on task_list.id = subtask_list.parent_task_id where start_date = :inputDate")
    fun getSingleDayTaskWithSubTasks(inputDate: String) : List<TaskWithSubTasks>
}