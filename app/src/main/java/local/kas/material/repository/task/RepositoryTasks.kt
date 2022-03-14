package local.kas.material.repository.task

import local.kas.material.model.tasks.Task

interface RepositoryTasks {
    fun getAllTasks(): List<Task>
    fun saveTask(task: Task)
}