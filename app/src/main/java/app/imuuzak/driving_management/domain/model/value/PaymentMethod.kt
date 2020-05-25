package app.imuuzak.driving_management.domain.model.value

import app.imuuzak.driving_management.R

enum class PaymentMethod {
    NONE {
        override fun value() = -1
        override fun textResId() = -1
    },
    // カード払い
    CARD {
        override fun value() = 10
        override fun textResId() = R.string.payment_method_card
    },
    // 銀行払い
    BANK {
        override fun value() = 20
        override fun textResId() = R.string.payment_method_bank
    },
    // 決済サービス払い
    PAYMENT_SERVICE {
        override fun value() = 30
        override fun textResId() = R.string.payment_method_payment_service
    },
    // コンビニ払い
    CONVENIENCE_STORE {
        override fun value() = 40
        override fun textResId() = R.string.payment_method_convenience_store
    },
    // 現地払い
    LOCAL {
        override fun value() = 50
        override fun textResId() = R.string.payment_method_local
    },
    // その他
    OTHER {
        override fun value() = 60
        override fun textResId() = R.string.payment_method_other
    };

    abstract fun value(): Int
    abstract fun textResId(): Int

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