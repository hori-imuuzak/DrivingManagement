package app.imuuzak.driving_management.domain.model

import app.imuuzak.driving_management.domain.model.value.WeatherType
import java.io.Serializable

data class Weather(
    val type: WeatherType,

    // 座標
    val lat: Double? = null,
    val lng: Double? = null,

    // 郵便番号
    val zipCode: String? = null,

    // 気温
    val temp: Double? = null,

    // 最高気温
    val maxTemp: Double? = null,
    // 最低気温
    val minTemp: Double? = null,

    // 気圧
    val pressure: Double? = null,

    // 湿度
    val humidity: Double? = null,

    // 雲の量
    val clouds: Int? = null,

    // 風速
    val windSpeed: Double? = null,
    // 風向き
    val windDirection: Double? = null
) : Serializable