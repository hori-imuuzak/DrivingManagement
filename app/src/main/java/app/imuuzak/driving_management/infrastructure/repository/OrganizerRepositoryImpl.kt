package app.imuuzak.driving_management.infrastructure.repository

import app.imuuzak.driving_management.domain.model.Organizer
import app.imuuzak.driving_management.domain.repository.OrganizerRepository
import app.imuuzak.driving_management.infrastructure.database.AppDatabase
import app.imuuzak.driving_management.infrastructure.database.entity.BankAccountEntity
import app.imuuzak.driving_management.infrastructure.database.entity.OrganizerEntity
import javax.inject.Inject

class OrganizerRepositoryImpl @Inject constructor(val database: AppDatabase) : OrganizerRepository {
    override suspend fun getAll(): List<Organizer> {
        val list = database.organizerDao().getAll()
        val organizers = list.map {
            it.organizer.toOrganizer(it.bankAccounts.map { bankAccount -> bankAccount.toBankAccount() })
        }

        return organizers
    }

    override suspend fun create(organizer: Organizer): Organizer? {
        val organizerEntity = OrganizerEntity.fromOrganizer(organizer)

        val getLastInsertRowId = database.organizerDao().createOrganizer(organizerEntity)

        database.organizerDao().createBankAccount(
            organizer.bankAccount.map {
                BankAccountEntity.fromBankAccount(getLastInsertRowId, it)
            }
        )

        return organizer
    }
}