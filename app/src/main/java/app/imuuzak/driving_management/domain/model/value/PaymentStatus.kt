package app.imuuzak.driving_management.domain.model.value

import app.imuuzak.driving_management.R

enum class PaymentStatus {
    NONE {
        override fun value() = -1
        override fun textResId() = -1
    },
    // 未払い
    YET {
        override fun value() = 10
        override fun textResId() = R.string.payment_status_yet
    },
    // 支払済み
    DONE {
        override fun value() = 20
        override fun textResId() = R.string.payment_status_done
    },
    // 期限切れ
    EXPIRED {
        override fun value() = 30
        override fun textResId() = R.string.payment_status_expired
    };

    abstract fun value(): Int
    abstract fun textResId(): Int

    companion object {
        fun fromValue(value: Int): PaymentStatus {
            return when(value) {
                YET.value() -> YET
                DONE.value() -> DONE
                EXPIRED.value() -> EXPIRED
                else -> NONE
            }
        }
    }
}