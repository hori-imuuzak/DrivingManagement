package app.imuuzak.driving_management.infrastructure.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import app.imuuzak.driving_management.infrastructure.database.entity.CircuitEntity

@Dao
interface CircuitDao {
    @Query("SELECT * FROM circuits")
    suspend fun getAll(): List<CircuitEntity>

    @Query("SELECT * FROM circuits WHERE name = :name")
    suspend fun findByName(name: String): List<CircuitEntity>

    @Insert
    suspend fun create(circuit: CircuitEntity)
}