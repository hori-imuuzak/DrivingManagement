package app.imuuzak.driving_management.infrastructure.repository

import app.imuuzak.driving_management.domain.model.Circuit
import app.imuuzak.driving_management.domain.model.Record
import app.imuuzak.driving_management.domain.model.value.RecordTime
import app.imuuzak.driving_management.domain.repository.RecordRepository
import app.imuuzak.driving_management.infrastructure.repository.entity.FirebaseCircuitEntity
import app.imuuzak.driving_management.infrastructure.repository.entity.FirebaseRecordEntity
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await
import java.util.*

class RecordRepositoryImpl : RecordRepository {
    override suspend fun getRecord(circuit: Circuit): List<Record> {
        val auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        val result = mutableListOf<Record>()

        user?.let {
            val snapshot = FirebaseRecordEntity
                .collections(it.uid)
                .whereEqualTo("circuit.name", circuit.name)
                .orderBy("date", Query.Direction.DESCENDING)
                .get()
                .await()

            result.addAll(snapshot.documents.map { doc ->
                Record(
                    circuit = circuit,
                    date = (doc["date"] as Timestamp).toDate(),
                    recordList = (doc["recordList"] as ArrayList<*>).map { recordTime -> RecordTime.fromString(recordTime as String) }.toList(),
                    memo = doc["memo"] as String,
                    pictureUrlList = listOf()
                )
            })
        }

        return result
    }

    override suspend fun createRecord(record: Record): Record? {
        val auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        var createdRecord: Record? = null
        user?.let {
            FirebaseRecordEntity
                .collections(it.uid)
                .document()
                .set(FirebaseRecordEntity.from(record))
                .addOnSuccessListener {
                    createdRecord = record
                }
                .await()

            // レコードを記録しているサーキットをリストに追加
            FirebaseRecordEntity
                .hasCircuitList(it.uid)
                .document(record.circuit.name)
                .set(FirebaseCircuitEntity.from(record.circuit))
                .await()
        }

        return createdRecord
    }
}