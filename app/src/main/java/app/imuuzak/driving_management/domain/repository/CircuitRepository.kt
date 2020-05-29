package app.imuuzak.driving_management.domain.repository

import app.imuuzak.driving_management.domain.model.Circuit

interface CircuitRepository {
    suspend fun getAll(): List<Circuit>
    suspend fun getHasRecord(): List<Circuit>
    suspend fun create(circuit: Circuit): Circuit?
}