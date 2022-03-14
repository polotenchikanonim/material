package local.kas.material.room.tasks

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TasksDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entity: TasksEntity)

    @Query("SELECT * FROM task_entity")
    fun getAllHistoryWeather(): List<TasksEntity>

}


//    @Delete
//    fun delete(entity: HistoryWeatherEntity)
//
//    @Update
//    fun update(entity: HistoryWeatherEntity)
//
//    // lesson 9
//    @Query("SELECT * FROM history_weather_entity WHERE id=:id")
//    fun getHistoryLine(id: Long): Cursor