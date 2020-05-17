package app.imuuzak.driving_management.infrastructure.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "circuits")
data class CircuitEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "kana") val kana: String,
    @ColumnInfo(name = "url") val url: String
)