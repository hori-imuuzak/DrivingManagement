package app.imuuzak.driving_management.infrastructure.repository.entity

import app.imuuzak.driving_management.domain.model.Circuit
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.PropertyName

data class FirebaseCircuitEntity(
    // 名称
    var name: String = "",

    // 名称（カナ）
    var kana: String = "",

    // URL
    var url: String = ""
) {
    companion object {
        fun collections(): CollectionReference {
            return FirebaseFirestore.getInstance()
                .collection("circuit")
        }

        fun from(model: Circuit?): FirebaseCircuitEntity {
            return FirebaseCircuitEntity(
                name = model?.name ?: "",
                kana = model?.kana ?: "",
                url = model?.url ?: ""
            )
        }
    }
}