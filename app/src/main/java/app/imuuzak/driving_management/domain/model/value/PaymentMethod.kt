package app.imuuzak.driving_management.domain.model.value

enum class PaymentMethod {
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
}