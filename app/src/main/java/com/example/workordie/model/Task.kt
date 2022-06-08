package com.example.workordie.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "task_list")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @NonNull @ColumnInfo(name = "task_type")
    var taskType: String,
    @NonNull @ColumnInfo(name = "task_name")
    var taskName: String,
    @NonNull @ColumnInfo(name = "start_date")
    var startDate: Date,
    @NonNull @ColumnInfo(name = "end_date")
    var endDate: Date,
    @ColumnInfo(name = "subtask_list")
    var subtaskList: List<SubTask>,
    @ColumnInfo(name = "total_time_spent")
    var totalTimeSpent: Double
)
