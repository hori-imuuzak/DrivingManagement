package app.imuuzak.driving_management.infrastructure.repository.entity

import app.imuuzak.driving_management.domain.model.Record
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class FirebaseRecordEntity(
    val circuit: FirebaseCircuitEntity,

    val date: Date,

    val recordList: List<String>,

    val memo: String = "",

    val pictureUrlList: List<String> = listOf()
) {
    companion object {
        fun collections(uid: String): CollectionReference {
            return FirebaseFirestore
                .getInstance()
                .collection("users")
                .document(uid)
                .collection("records")
        }

        fun hasCircuitList(uid: String): CollectionReference {
            return FirebaseFirestore
                .getInstance()
                .collection("users")
                .document(uid)
                .collection("records")
                .document("circuit")
                .collection("has_record")
        }

        fun from(record: Record): FirebaseRecordEntity {
            return FirebaseRecordEntity(
                circuit = FirebaseCircuitEntity.from(record.circuit),
                date = record.date,
                recordList = record.recordList.map { it.format() },
                memo = record.memo,
                pictureUrlList = record.pictureUrlList
            )
        }
    }
}