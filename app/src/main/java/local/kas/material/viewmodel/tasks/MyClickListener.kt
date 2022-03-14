package local.kas.material.viewmodel.tasks

import local.kas.material.model.tasks.Task

fun interface MyClickListener {
    fun onItemClick(task: Task)
}