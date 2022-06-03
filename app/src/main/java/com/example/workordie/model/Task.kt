package com.example.workordie.model

import java.util.*

data class Task(
    var name: String,
    var startDate: Date,
    var endDate: Date,
    var subtaskList: List<SubTask>,
    var totalTimeSpent: Double,
    var taskType: String
)
