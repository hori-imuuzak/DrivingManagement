package app.imuuzak.driving_management.infrastructure.repository.entity

import app.imuuzak.driving_management.domain.model.TrackEvent
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.PropertyName
import java.util.*

data class FirebaseTrackEventEntity(
    // 開催サーキット
    @PropertyName("circuit")
    var circuit: FirebaseCircuitEntity? = null,

    // 主催者
    @PropertyName("organizer")
    var organizer: FirebaseOrganizerEntity? = null,

    // 持ち物
    // var belongings: List<Belonging> = listOf(),

    // 開催日程
    @PropertyName("date_start")
    var dateStart: Date? = null,
    @PropertyName("date_end")
    var dateEnd: Date? = null,

    // 集合時間
    @PropertyName("meeting_time_hour")
    var meetingTimeHour: Int? = null,
    @PropertyName("meeting_time_minute")
    var meetingTimeMinute: Int? = null,

    // 解散時間
    @PropertyName("dismissal_time_hour")
    var dismissalTimeHour: Int? = null,
    @PropertyName("dismissal_time_minute")
    var dismissalTimeMinute: Int? = null,

    // 注意事項
    @PropertyName("precautions")
    var precautions: String? = "",

    // 料金
    @PropertyName("price")
    var price: Int? = 0,

    // 支払い方法
    @PropertyName("payment_method")
    var paymentMethod: Int? = null,

    // 支払い期限
    @PropertyName("payment_deadline")
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
                dateStart = model.date?.begin,
                dateEnd = model.date?.end,
                meetingTimeHour = model.meetingTime?.hour,
                dismissalTimeMinute = model.meetingTime?.minute,
                precautions = model.precautions ?: "",
                price = model.price ?: 0,
                paymentMethod = model.paymentMethod?.value(),
                paymentDeadline = model.paymentDeadline?.begin
            )
        }
    }
}