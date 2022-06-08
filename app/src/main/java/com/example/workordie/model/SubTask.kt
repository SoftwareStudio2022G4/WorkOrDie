package com.example.workordie.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Time

@Entity(tableName = "subtask_list")
data class SubTask(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @NonNull @ColumnInfo(name = "subtask_name")
    var subTaskName: String,
    @NonNull @ColumnInfo(name = "has_done")
    var hasDone: Boolean,
    @ColumnInfo(name = "time_spent")
    var timeSpent: Double
)
