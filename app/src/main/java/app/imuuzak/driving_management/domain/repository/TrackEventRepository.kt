package app.imuuzak.driving_management.domain.repository

import app.imuuzak.driving_management.domain.model.TrackEvent

interface TrackEventRepository {
    suspend fun create(trackEvent: TrackEvent): TrackEvent?
}