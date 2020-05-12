package app.imuuzak.driving_management.domain.model.value

enum class AccountType {
    SAVINGS_ACCOUNT {
        override fun value() = 1
    },
    CURRENT_ACCOUNT {
        override fun value() = 2
    };

    abstract fun value(): Int
}