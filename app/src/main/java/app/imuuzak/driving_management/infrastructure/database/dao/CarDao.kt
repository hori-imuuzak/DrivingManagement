package app.imuuzak.driving_management.infrastructure.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import app.imuuzak.driving_management.infrastructure.database.entity.CarEntity

@Dao
interface CarDao {
    @Query("SELECT * FROM cars")
    suspend fun getAll(): List<CarEntity>

    @Insert
    suspend fun create(car: CarEntity)

    @Update
    suspend fun update(car: CarEntity)
}