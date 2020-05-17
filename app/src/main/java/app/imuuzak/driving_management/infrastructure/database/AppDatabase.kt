package app.imuuzak.driving_management.infrastructure.database

import androidx.room.Database
import androidx.room.RoomDatabase
import app.imuuzak.driving_management.infrastructure.database.dao.CircuitDao
import app.imuuzak.driving_management.infrastructure.database.entity.CircuitEntity

@Database(
    entities = [CircuitEntity::class],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun circuitDao(): CircuitDao
}