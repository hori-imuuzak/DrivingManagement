package app.imuuzak.driving_management.infrastructure.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import app.imuuzak.driving_management.infrastructure.database.entity.BankAccountEntity
import app.imuuzak.driving_management.infrastructure.database.entity.OrganizerEntity
import app.imuuzak.driving_management.infrastructure.database.entity.OrganizerHasBankAccount

@Dao
interface OrganizerDao {
    @Transaction
    @Query("SELECT * FROM organizers")
    suspend fun getAll(): List<OrganizerHasBankAccount>

    @Insert
    suspend fun createOrganizer(organizer: OrganizerEntity): Long

    @Insert
    suspend fun createBankAccount(bankAccount: List<BankAccountEntity>)
}