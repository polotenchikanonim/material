package local.kas.material.model.tasks

import local.kas.material.Database
import local.kas.material.repository.task.RepositoryTasks
import local.kas.material.room.tasks.TasksEntity

class RepositoryTasksImpl : RepositoryTasks {

    override fun getAllTasks(): List<Task> {
        TODO("Not yet implemented")
    }

    override fun saveTask(task: Task) {
        Thread {
            Database.getTasksDao().insert(
                convertTaskToTaskEntity(task)
            )
        }.start()
    }

    private fun convertTaskToTaskEntity(task: Task) =
        TasksEntity(
            0,
            task.title,
            task.description,
            task.type
        )
}