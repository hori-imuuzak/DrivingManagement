package app.imuuzak.driving_management.infrastructure.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import app.imuuzak.driving_management.infrastructure.database.entity.WeatherEntity

@Dao
interface WeatherDao {
    @Query("SELECT * FROM weathers WHERE lat = :lat AND lng = :lng LIMIT 1")
    suspend fun findByLatLng(lat: Double, lng: Double): List<WeatherEntity>

    @Query("SELECT * FROM weathers WHERE zip_code = :zipCode LIMIT 1")
    suspend fun findByZipCode(zipCode: String): List<WeatherEntity>

    @Insert
    suspend fun insert(weather: WeatherEntity)
}