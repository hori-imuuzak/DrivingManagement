package app.imuuzak.driving_management.infrastructure.repository

import app.imuuzak.driving_management.domain.model.Circuit
import app.imuuzak.driving_management.domain.repository.CircuitRepository
import app.imuuzak.driving_management.infrastructure.database.AppDatabase
import app.imuuzak.driving_management.infrastructure.database.entity.CircuitEntity
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class CircuitRepositoryImpl @Inject constructor(val database: AppDatabase) : CircuitRepository {
    override suspend fun getAll(): List<Circuit> {
        val circuitList = mutableListOf<Circuit>()

        val snapshot = FirebaseFirestore
            .getInstance()
            .collection("circuit")
            .get()
            .await()
        circuitList.addAll(snapshot.documents.map { doc ->
            Circuit(
                name = doc["name"] as String,
                kana = doc["kana"] as String,
                url = doc["url"] as String
            )
        })

        circuitList.addAll(database.circuitDao().getAll().map { entity ->
            Circuit(
                name = entity.name,
                kana = entity.kana,
                url = entity.url
            )
        })

        return circuitList
    }

    override suspend fun create(circuit: Circuit): Circuit? {
        val entity =
            CircuitEntity(id = 0, name = circuit.name, kana = circuit.kana, url = circuit.url)
        database.circuitDao().create(entity)
        return circuit
    }
}