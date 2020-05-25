package app.imuuzak.driving_management.domain.repository

import app.imuuzak.driving_management.domain.model.Weather

interface WeatherRepository {
    suspend fun getByLatLng(lat: Double, lng: Double): Weather
    suspend fun getByZipCode(zipCode: String): Weather
}