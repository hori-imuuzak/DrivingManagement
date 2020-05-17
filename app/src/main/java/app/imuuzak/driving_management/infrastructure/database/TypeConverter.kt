package app.imuuzak.driving_management.infrastructure.database

import androidx.room.TypeConverter
import app.imuuzak.driving_management.domain.model.value.AccountType

class TypeConverter {
    @TypeConverter
    fun bankAccountTypeToInt(accountType: AccountType): Int {
        return accountType.value()
    }

    @TypeConverter
    fun intToBankAccountType(accountType: Int): AccountType {
        return when(accountType) {
            AccountType.SAVINGS_ACCOUNT.value() -> AccountType.SAVINGS_ACCOUNT
            AccountType.CURRENT_ACCOUNT.value() -> AccountType.CURRENT_ACCOUNT
            else -> AccountType.SAVINGS_ACCOUNT
        }
    }
}