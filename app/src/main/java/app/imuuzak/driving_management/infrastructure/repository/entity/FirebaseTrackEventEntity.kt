package app.imuuzak.driving_management.infrastructure.repository.entity

import app.imuuzak.driving_management.domain.model.TrackEvent
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.PropertyName
import java.util.*

data class FirebaseTrackEventEntity(
    // 開催サーキット
    var circuit: FirebaseCircuitEntity? = null,

    // 主催者
    var organizer: FirebaseOrganizerEntity? = null,

    // 持ち物
    var belongings: List<FirebaseBelongingEntity>? = null,

    // 開催日程
    var dateStart: Date? = null,
    var dateEnd: Date? = null,

    // 集合時間
    var meetingTimeHour: Int? = null,
    var meetingTimeMinute: Int? = null,

    // 解散時間
    var dismissalTimeHour: Int? = null,
    var dismissalTimeMinute: Int? = null,

    // 注意事項
    var precautions: String? = "",

    // 料金
    var price: Int? = 0,

    // 支払い方法
    var paymentMethod: Int? = null,

    // 支払いステータス
    var paymentStatus: Int? = null,

    // 支払い期限
    var paymentDeadline: Date? = null
) {
    companion object {
        fun collections(uid: String): CollectionReference {
            return FirebaseFirestore.getInstance()
                .collection("users")
                .document(uid)
                .collection("schedules")
        }

        fun from(model: TrackEvent): FirebaseTrackEventEntity {
            return FirebaseTrackEventEntity(
                circuit = FirebaseCircuitEntity.from(model.circuit),
                organizer = FirebaseOrganizerEntity.from(model.organizer),
                belongings = model.belongings.map { FirebaseBelongingEntity.from(it) },
                dateStart = model.date?.begin,
                dateEnd = model.date?.end,
                meetingTimeHour = model.meetingTime?.hour,
                meetingTimeMinute = model.meetingTime?.minute,
                dismissalTimeHour = model.dismissalTime?.hour,
                dismissalTimeMinute = model.dismissalTime?.minute,
                precautions = model.precautions ?: "",
                price = model.price ?: 0,
                paymentMethod = model.paymentMethod?.value(),
                paymentStatus = model.paymentStatus?.value(),
                paymentDeadline = model.paymentDeadline?.begin
            )
        }
    }
}