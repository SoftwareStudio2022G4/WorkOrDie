package com.example.workordie.model

import androidx.room.Embedded
import androidx.room.Relation
import com.example.workordie.model.Task

class TaskWithSubTasks (
    @Embedded
    var task: Task,
    @Relation(parentColumn = "id",entityColumn = "parent_task_id")
    var subTasks:List<SubTask>
)