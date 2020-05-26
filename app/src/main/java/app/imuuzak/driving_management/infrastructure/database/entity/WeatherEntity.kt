package app.imuuzak.driving_management.infrastructure.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import app.imuuzak.driving_management.domain.model.Weather
import app.imuuzak.driving_management.domain.model.value.WeatherType

@Entity(tableName = "weathers")
data class WeatherEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = "type")
    val type: Int,

    // 座標
    @ColumnInfo(name = "lat")
    val lat: Double? = null,
    @ColumnInfo(name = "lng")
    val lng: Double? = null,

    // 郵便番号
    @ColumnInfo(name = "zip_code")
    val zipCode: String? = null,

    // 気温
    @ColumnInfo(name = "temp")
    val temp: Double? = null,

    // 最高気温
    @ColumnInfo(name = "max_temp")
    val maxTemp: Double? = null,
    // 最低気温
    @ColumnInfo(name = "min_temp")
    val minTemp: Double? = null,

    // 気圧
    @ColumnInfo(name = "pressure")
    val pressure: Double? = null,

    // 湿度
    @ColumnInfo(name = "humidity")
    val humidity: Double? = null,

    // 雲の量
    @ColumnInfo(name = "clouds")
    val clouds: Int? = null,

    // 風速
    @ColumnInfo(name = "wind_speed")
    val windSpeed: Double? = null,
    // 風向き
    @ColumnInfo(name = "wind_direction")
    val windDirection: Double? = null
) {
    fun toWeather() = Weather(
        type = WeatherType.fromValue(this.type),
        lat = this.lat,
        lng = this.lng,
        zipCode = this.zipCode,
        temp = this.temp,
        minTemp = this.minTemp,
        maxTemp = this.maxTemp,
        pressure = this.pressure,
        humidity = this.humidity,
        clouds = this.clouds,
        windSpeed = this.windSpeed,
        windDirection = this.windDirection
    )

    companion object {
        fun fromWeather(model: Weather): WeatherEntity {
            return WeatherEntity(
                id = 0,
                type = model.type.value(),
                lat = model.lat,
                lng = model.lng,
                zipCode = model.zipCode,
                temp = model.temp,
                minTemp = model.minTemp,
                maxTemp = model.maxTemp,
                pressure = model.pressure,
                humidity = model.humidity,
                clouds = model.clouds,
                windSpeed = model.windSpeed,
                windDirection = model.windDirection
            )
        }
    }
}