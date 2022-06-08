package com.example.workordie.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "task_list")
data class Task(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @NonNull
    @ColumnInfo(name = "task_type")
    var taskType: String,

    @NonNull
    @ColumnInfo(name = "task_name")
    var taskName: String,

    @NonNull
    @ColumnInfo(name = "start_date")
    var startDate: String,

    @NonNull
    @ColumnInfo(name = "end_date")
    var endDate: String,

//    @ColumnInfo(name = "subtask_list")
//    var subtaskList: List<SubTask>,

    @ColumnInfo(name = "total_time_spent")
    var totalTimeSpent: Double

)

@Entity(
    foreignKeys = [ForeignKey(
        entity = Task::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("parent_task_id"),
        onDelete = ForeignKey.CASCADE
    )],
    tableName = "subtask_list"
)
data class SubTask(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "parent_task_id")
    val parentTaskId: Int,
    @NonNull @ColumnInfo(name = "subtask_name")
    val subTaskName: String,
    @NonNull @ColumnInfo(name = "has_done")
    val hasDone: Boolean,
    @ColumnInfo(name = "time_spent")
    val timeSpent: Double
)
