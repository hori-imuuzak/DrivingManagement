package app.imuuzak.driving_management.infrastructure.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import app.imuuzak.driving_management.infrastructure.database.dao.CarDao
import app.imuuzak.driving_management.infrastructure.database.dao.CircuitDao
import app.imuuzak.driving_management.infrastructure.database.dao.OrganizerDao
import app.imuuzak.driving_management.infrastructure.database.dao.WeatherDao
import app.imuuzak.driving_management.infrastructure.database.entity.*

@Database(
    entities = [
        CircuitEntity::class,
        OrganizerEntity::class,
        BankAccountEntity::class,
        WeatherEntity::class,
        CarEntity::class
    ],
    version = 1
)
@TypeConverters(TypeConverter::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun circuitDao(): CircuitDao
    abstract fun organizerDao(): OrganizerDao
    abstract fun weatherDao(): WeatherDao
    abstract fun carDao(): CarDao
}