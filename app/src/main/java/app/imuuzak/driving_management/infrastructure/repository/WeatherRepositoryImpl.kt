package app.imuuzak.driving_management.infrastructure.repository

import app.imuuzak.driving_management.BuildConfig
import app.imuuzak.driving_management.domain.model.Weather
import app.imuuzak.driving_management.domain.repository.WeatherRepository
import app.imuuzak.driving_management.infrastructure.database.AppDatabase
import app.imuuzak.driving_management.infrastructure.database.entity.WeatherEntity
import app.imuuzak.driving_management.infrastructure.restapi.OpenWeatherMapApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(val database: AppDatabase): WeatherRepository {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.openweathermap.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val openWeatherMapApiService = retrofit.create(OpenWeatherMapApi::class.java)

    override suspend fun getByLatLng(lat: Double, lng: Double): Weather {
        val cache = database.weatherDao().findByLatLng(lat, lng)
        if (cache.isNotEmpty()) {
            return cache.first().toWeather()
        }

        val response = openWeatherMapApiService.getByLatLng(lat, lng, appid = BuildConfig.OPEN_WEATHER_API_KEY)
        val weather = response.toWeather()
        database.weatherDao().insert(WeatherEntity.fromWeather(weather))
        return weather
    }

    override suspend fun getByZipCode(zipCode: String): Weather {
        val cache = database.weatherDao().findByZipCode(zipCode)
        if (cache.isNotEmpty()) {
            return cache.first().toWeather()
        }

        val response = openWeatherMapApiService.getByZipCode("${zipCode},jp", appid = BuildConfig.OPEN_WEATHER_API_KEY)
        val weather = response.toWeather(zipCode = zipCode)
        database.weatherDao().insert(WeatherEntity.fromWeather(weather))
        return weather
    }
}