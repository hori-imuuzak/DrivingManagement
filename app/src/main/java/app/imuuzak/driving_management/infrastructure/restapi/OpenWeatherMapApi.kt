package app.imuuzak.driving_management.infrastructure.restapi

import app.imuuzak.driving_management.infrastructure.repository.entity.OpenWeatherMapEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherMapApi {
    @GET("data/2.5/weather")
    suspend fun getByLatLng(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units: String = "metric",
        @Query("lang") lang: String = "ja",
        @Query("appid") appid: String
    ): OpenWeatherMapEntity

    // TODO zipCode format {zipCode},{country code}
    // ref: https://openweathermap.org/current#zip
    @GET("data/2.5/weather")
    suspend fun getByZipCode(
        @Query("zip") zipCode: String,
        @Query("units") units: String = "metric",
        @Query("lang") lang: String = "ja",
        @Query("appid") appid: String
    ): OpenWeatherMapEntity
}