package app.imuuzak.driving_management.domain.repository

import app.imuuzak.driving_management.domain.model.TrackEvent
import app.imuuzak.driving_management.domain.model.value.Pagination

interface TrackEventRepository {
    suspend fun get(pagination: Pagination): List<TrackEvent>
    suspend fun create(trackEvent: TrackEvent): TrackEvent?
}