package local.kas.material

import androidx.room.Room
import local.kas.material.model.tasks.Task
import local.kas.material.room.tasks.TasksDao
import local.kas.material.room.tasks.TasksDataBase

class Database {

    companion object {

        private const val DB_NAME = "history.db"

        private var db = Room.databaseBuilder(
            App.appInstance, TasksDataBase::class.java,
            DB_NAME
        ).build()

        fun getTasksDao(): TasksDao {
            return db.tasksDao()
        }

        fun saveTask(task: Task) {

        }
    }
}