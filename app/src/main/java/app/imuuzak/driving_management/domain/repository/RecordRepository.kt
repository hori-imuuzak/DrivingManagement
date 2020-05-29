package app.imuuzak.driving_management.domain.repository

import app.imuuzak.driving_management.domain.model.Circuit
import app.imuuzak.driving_management.domain.model.Record

interface RecordRepository {
    suspend fun getRecord(circuit: Circuit): List<Record>
    suspend fun createRecord(record: Record): Record?
}