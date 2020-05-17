package app.imuuzak.driving_management.domain.repository

import app.imuuzak.driving_management.domain.model.Organizer

interface OrganizerRepository {
    suspend fun getAll(): List<Organizer>
    suspend fun create(organizer: Organizer): Organizer?
}