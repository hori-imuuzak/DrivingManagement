package app.imuuzak.driving_management.infrastructure.repository

import app.imuuzak.driving_management.domain.model.TrackEvent
import app.imuuzak.driving_management.domain.repository.TrackEventRepository

class TrackEventRepositoryImpl: TrackEventRepository {
    override suspend fun create(trackEvent: TrackEvent): TrackEvent? {
        return trackEvent
    }
}