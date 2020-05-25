package app.imuuzak.driving_management.infrastructure.repository.entity

import app.imuuzak.driving_management.domain.model.Weather
import com.google.gson.annotations.SerializedName

data class OpenWeatherMapEntity(
    val coord: Coord,
    val weather: List<Weather>,
    val main: Main,
    val wind: Wind,
    val clouds: Clouds
) {
    data class Coord(
        val lat: Double,
        val lon: Double
    )

    data class Weather(
        val description: String
    )

    data class Main(
        val temp: Double,
        @SerializedName("feels_like")
        val feelsLike: Double,
        @SerializedName("temp_min")
        val tempMin: Double,
        @SerializedName("temp_max")
        val tempMax: Double,
        val pressure: Double,
        val humidity: Double
    )

    data class Clouds(
        val all: Int
    )

    data class Wind(
        val speed: Double,
        val deg: Double
    )

    fun toWeather(zipCode: String? = null) = Weather(
        lat = this.coord.lat,
        lng = this.coord.lon,
        zipCode = zipCode,
        temp = this.main.temp,
        maxTemp = this.main.tempMax,
        minTemp = this.main.tempMin,
        pressure = this.main.pressure,
        humidity = this.main.humidity,
        clouds = this.clouds.all,
        windSpeed = this.wind.speed,
        windDirection = this.wind.deg
    )
}