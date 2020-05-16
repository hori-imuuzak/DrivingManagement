package app.imuuzak.driving_management.domain.repository

import androidx.lifecycle.LiveData
import app.imuuzak.driving_management.domain.model.Circuit

interface CircuitRepository {
    fun getAll(): LiveData<List<Circuit>>
    fun create(circuit: Circuit): LiveData<Circuit?>
}