package app.imuuzak.driving_management.infrastructure.repository

import app.imuuzak.driving_management.domain.model.TrackEvent
import app.imuuzak.driving_management.domain.repository.TrackEventRepository
import app.imuuzak.driving_management.infrastructure.repository.entity.FirebaseTrackEventEntity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class TrackEventRepositoryImpl : TrackEventRepository {
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