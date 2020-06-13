package app.imuuzak.driving_management.domain.repository

import app.imuuzak.driving_management.domain.model.Car

interface CarRepository {
    suspend fun getAll(): List<Car>
    suspend fun create(car: Car): Car?
    suspend fun update(car: Car): Car?
}