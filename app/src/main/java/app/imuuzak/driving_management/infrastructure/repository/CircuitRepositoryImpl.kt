package app.imuuzak.driving_management.infrastructure.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import app.imuuzak.driving_management.domain.model.Circuit
import app.imuuzak.driving_management.domain.repository.CircuitRepository
import com.google.firebase.firestore.FirebaseFirestore

class CircuitRepositoryImpl : CircuitRepository {
    override fun getAll(): LiveData<List<Circuit>> {
        val data = MutableLiveData<List<Circuit>>()

        FirebaseFirestore
            .getInstance()
            .collection("circuit")
            .get()
            .addOnSuccessListener {
                data.value = it.documents.map { doc ->
                    Circuit(
                        name = doc["name"] as String,
                        kana = doc["kana"] as String,
                        url = doc["url"] as String
                    )
                }
            }

        return data
    }

    override fun create(circuit: Circuit): LiveData<Circuit?> {
        return MutableLiveData<Circuit?>().apply { value = null }
    }
}