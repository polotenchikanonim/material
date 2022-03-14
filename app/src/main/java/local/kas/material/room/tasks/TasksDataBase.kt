package local.kas.material.room.tasks

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TasksEntity::class], version = 1)
abstract class TasksDataBase : RoomDatabase() {
    abstract fun tasksDao(): TasksDao
}