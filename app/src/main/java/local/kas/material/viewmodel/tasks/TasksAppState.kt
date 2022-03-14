package local.kas.material.viewmodel.tasks

import local.kas.material.model.tasks.Task

sealed class TasksAppState {
    data class Success(val tasks: List<Task>) : TasksAppState()
}
