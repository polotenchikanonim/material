package local.kas.material.room.tasks

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "task_entity")
data class TasksEntity(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    var title: String = "title",
    var description: String = "description",
    var type: Int = 0,
)

