package app.imuuzak.driving_management.infrastructure.repository.entity

import app.imuuzak.driving_management.domain.model.Weather
import app.imuuzak.driving_management.domain.model.value.WeatherType
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
        val main: String,
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
        type = weatherMainToWeatherTypeValue(this.weather.first().main, this.clouds.all),
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

    private fun weatherMainToWeatherTypeValue(main: String, clouds: Int = 0): WeatherType {
        return when (main) {
            "Thunderstorm" -> WeatherType.STORM
            "Drizzle" -> WeatherType.FOG
            "Rain" -> WeatherType.RAINY
            "Snow" -> WeatherType.SNOW
            "Mist", "Smoke", "Haze", "Dust", "Fog", "Sand", "Ash", "Squall", "Tornado" -> WeatherType.FOG
            "Clear" -> WeatherType.CLEAR
            "Clouds" -> {
                if (clouds <= 25) WeatherType.SUNNY
                else WeatherType.CLOUDY
            }
            else -> WeatherType.NONE
        }
    }
}