package app.imuuzak.driving_management.infrastructure.database.entity

import androidx.room.Embedded
import androidx.room.Relation

class OrganizerHasBankAccount {
    @Embedded
    lateinit var organizer: OrganizerEntity

    @Relation(parentColumn = "id", entityColumn = "organizer_id")
    lateinit var bankAccounts: List<BankAccountEntity>
}