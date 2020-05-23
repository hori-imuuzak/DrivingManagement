package app.imuuzak.driving_management.domain.model

import app.imuuzak.driving_management.domain.model.value.PaymentMethod
import app.imuuzak.driving_management.domain.model.value.PaymentStatus
import app.imuuzak.driving_management.domain.model.value.Schedule
import app.imuuzak.driving_management.domain.model.value.Time

/**
 * 走行会
 */
data class TrackEvent(
    // 開催サーキット
    var circuit: Circuit?,
    // 主催者
    var organizer: Organizer?,
    // 持ち物
    var belongings: List<Belonging> = listOf(),
    // 開催日程
    var date: Schedule?,
    // 集合時間
    var meetingTime: Time? = Time(),
    // 解散時間
    var dismissalTime: Time? = Time(),
    // 注意事項
    var precautions: String? = "",
    // 料金
    var price: Int?,
    // 支払い方法
    var paymentMethod: PaymentMethod? = null,
    // 支払いステータス
    var paymentStatus: PaymentStatus? = PaymentStatus.YET,
    // 支払い期限
    var paymentDeadline: Schedule? = Schedule()
) {
    init {
        if (circuit == null) {
            throw IllegalArgumentException("circuit")
        }

        if (organizer == null) {
            throw IllegalArgumentException("organizer")
        }

        if (date == null) {
            throw IllegalArgumentException("date")
        }

        if (price == null) {
            throw IllegalArgumentException("price")
        }
    }
}