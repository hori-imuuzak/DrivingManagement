package app.imuuzak.driving_management.infrastructure.repository

import app.imuuzak.driving_management.domain.model.Circuit
import app.imuuzak.driving_management.domain.repository.CircuitRepository
import app.imuuzak.driving_management.infrastructure.database.AppDatabase
import app.imuuzak.driving_management.infrastructure.database.entity.CircuitEntity
import app.imuuzak.driving_management.infrastructure.repository.entity.FirebaseCircuitEntity
import app.imuuzak.driving_management.infrastructure.repository.entity.FirebaseRecordEntity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class CircuitRepositoryImpl @Inject constructor(val database: AppDatabase) : CircuitRepository {
    override suspend fun getAll(): List<Circuit> {
        val circuitList = mutableListOf<Circuit>()

        val snapshot = FirebaseCircuitEntity
            .collections()
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

    override suspend fun getHasRecord(): List<Circuit> {
        val circuitList = mutableListOf<Circuit>()

        val auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        user?.let {
            // レコードをもつサーキット名のリストを取得
            val hasRecordCircuitNameListSnapshot = FirebaseRecordEntity
                .hasCircuitList(it.uid)
                .get()
                .await()

            val circuitNameList = hasRecordCircuitNameListSnapshot.documents.map { doc ->
                doc.data?.get("name") as String
            }

            // RemoteDataのサーキットを取得
            val remoteCircuitListSnapshot = FirebaseCircuitEntity
                .collections()
                .whereIn("name", circuitNameList)
                .get()
                .await()

            // LocalDataのサーキットを取得
            val localCircuitList = database.circuitDao().findInNameList(circuitNameList)

            // RemoteData/LocalDataをそれぞれCircuitとしてリスト化
            circuitList.addAll(remoteCircuitListSnapshot.documents.map { doc ->
                val data = doc.data
                Circuit(
                    name = data?.get("name") as? String ?: "",
                    kana = data?.get("kana") as? String ?: "",
                    url = data?.get("url") as? String ?: ""
                )
            })
            circuitList.addAll(localCircuitList.map { entity ->
                Circuit(
                    name = entity.name,
                    kana = entity.kana,
                    url = entity.url
                )
            })
        }

        return circuitList
    }

    override suspend fun create(circuit: Circuit): Circuit? {
        val entity =
            CircuitEntity(id = 0, name = circuit.name, kana = circuit.kana, url = circuit.url)
        database.circuitDao().create(entity)
        return circuit
    }
}