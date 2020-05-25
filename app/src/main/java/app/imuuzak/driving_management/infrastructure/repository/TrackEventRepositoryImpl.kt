package app.imuuzak.driving_management.infrastructure.repository

import app.imuuzak.driving_management.domain.model.*
import app.imuuzak.driving_management.domain.model.value.*
import app.imuuzak.driving_management.domain.repository.TrackEventRepository
import app.imuuzak.driving_management.infrastructure.repository.entity.FirebaseTrackEventEntity
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class TrackEventRepositoryImpl : TrackEventRepository {
    override suspend fun get(pagination: Pagination): List<TrackEvent> {
        val auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        val result = mutableListOf<TrackEvent>()
        user?.let {
            val snapshot = FirebaseTrackEventEntity
                .collections(it.uid)
                .orderBy("dateStart")
                .limit(pagination.perPage.toLong())
                .get()
                .await()
            result.addAll(snapshot.documents.map { doc ->
                val circuit = doc["circuit"] as Map<*, *>
                val organizer = doc["organizer"] as Map<*, *>
                TrackEvent(
                    circuit = Circuit(
                        name = circuit["name"] as String,
                        kana = circuit["kana"] as String,
                        url = circuit["url"] as String
                    ),
                    organizer = Organizer(
                        name = organizer["name"] as String,
                        kana = organizer["kana"] as String,
                        representativeName = organizer["representativeName"] as String,
                        phoneNumber = organizer["phoneNumber"] as String,
                        email = organizer["email"] as String,
                        // TODO
                        bankAccount = listOf()
                    ),
                    // TODO
                    belongings = listOf(),
                    date = Schedule(
                        begin = (doc["dateStart"] as Timestamp).toDate(),
                        end = (doc["dateEnd"] as Timestamp).toDate()
                    ),
                    meetingTime = Time(
                        hour = (doc["meetingTimeHour"] as Long).toInt(),
                        minute = (doc["meetingTimeMinute"] as Long).toInt()
                    ),
                    dismissalTime = Time(
                        hour = (doc["dismissalTimeHour"] as Long).toInt(),
                        minute = (doc["dismissalTimeMinute"] as Long).toInt()
                    ),
                    precautions = doc["precautions"] as String,
                    price = (doc["price"] as Long).toInt(),
                    paymentMethod = PaymentMethod.fromValue((doc["paymentMethod"] as Long).toInt()),
                    paymentStatus = PaymentStatus.fromValue((doc["paymentStatus"] as Long).toInt()),
                    paymentDeadline = Schedule(
                        begin = (doc["paymentDeadline"] as Timestamp).toDate(),
                        end = (doc["paymentDeadline"] as Timestamp).toDate()
                    )
                )
            })
        }

        return result
    }

    override suspend fun create(trackEvent: TrackEvent): TrackEvent? {
        val auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        var createdTrackEvent: TrackEvent? = null

        user?.let {
            FirebaseTrackEventEntity
                .collections(user.uid)
                .document()
                .set(FirebaseTrackEventEntity.from(trackEvent))
                .addOnSuccessListener {
                    createdTrackEvent = trackEvent
                }.await()
        }

        return createdTrackEvent
    }
}