package app.imuuzak.driving_management.infrastructure.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import app.imuuzak.driving_management.domain.model.BankAccount
import app.imuuzak.driving_management.domain.model.value.AccountType

@Entity(
    tableName = "bank_accounts",
    foreignKeys = [
        ForeignKey(
            entity = OrganizerEntity::class,
            parentColumns = ["id"],
            childColumns = ["organizer_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class BankAccountEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "organizer_id") val organizerId: Long,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "branch_name") val branchName: String,
    @ColumnInfo(name = "account_type") val accountType: AccountType,
    @ColumnInfo(name = "holder_name") val holderName: String,
    @ColumnInfo(name = "account_number") val accountNumber: String
) {
    fun toBankAccount(): BankAccount {
        return BankAccount(
            bankCode = "",
            bankName = name,
            branchCode = "",
            branchName = branchName,
            accountType = accountType,
            holderName = holderName,
            accountNumber = accountNumber
        )
    }

    companion object {
        fun fromBankAccount(organizerId: Long, bankAccount: BankAccount): BankAccountEntity {
            return BankAccountEntity(
                0,
                organizerId = organizerId,
                name = bankAccount.bankName,
                branchName = bankAccount.branchName,
                accountType = bankAccount.accountType,
                holderName = bankAccount.holderName,
                accountNumber = bankAccount.accountNumber
            )
        }
    }
}