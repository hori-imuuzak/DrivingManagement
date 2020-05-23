package app.imuuzak.driving_management.domain.model.value

enum class PaymentMethod {
    NONE {
        override fun value() = -1
    },
    // カード払い
    CARD {
        override fun value() = 10
    },
    // 銀行払い
    BANK {
        override fun value() = 20
    },
    // 決済サービス払い
    PAYMENT_SERVICE {
        override fun value() = 30
    },
    // コンビニ払い
    CONVENIENCE_STORE {
        override fun value() = 40
    },
    // 現地払い
    LOCAL {
        override fun value() = 50
    },
    // その他
    OTHER {
        override fun value() = 60
    };

    abstract fun value(): Int

    companion object {
        fun fromValue(value: Int): PaymentMethod {
           return when(value) {
                CARD.value() -> CARD
                BANK.value() -> BANK
                PAYMENT_SERVICE.value() -> PAYMENT_SERVICE
                CONVENIENCE_STORE.value() -> CONVENIENCE_STORE
                LOCAL.value() -> LOCAL
                OTHER.value() -> OTHER
                else -> NONE
            }
        }
    }
}