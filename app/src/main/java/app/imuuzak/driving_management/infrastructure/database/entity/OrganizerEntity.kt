package app.imuuzak.driving_management.infrastructure.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import app.imuuzak.driving_management.domain.model.BankAccount
import app.imuuzak.driving_management.domain.model.Organizer
import app.imuuzak.driving_management.domain.model.value.AccountType

@Entity(tableName = "organizers")
data class OrganizerEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "kana") val kana: String,
    @ColumnInfo(name = "representative_name") val representativeName: String = "",
    @ColumnInfo(name = "phone_number") val phoneNumber: String = "",
    @ColumnInfo(name = "email") val email: String = ""
) {
    fun toOrganizer(bankAccount: List<BankAccount> = listOf()): Organizer {
        return Organizer(
            name = name,
            kana = kana,
            representativeName = representativeName,
            phoneNumber = phoneNumber,
            email = email,
            bankAccount = bankAccount
        )
    }

    companion object {
        fun fromOrganizer(organizer: Organizer): OrganizerEntity {
            return OrganizerEntity(
                0,
                name = organizer.name,
                kana = organizer.kana,
                representativeName = organizer.representativeName,
                phoneNumber = organizer.phoneNumber,
                email = organizer.email
            )
        }
    }
}