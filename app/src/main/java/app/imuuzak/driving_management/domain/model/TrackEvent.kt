package app.imuuzak.driving_management.domain.model

import app.imuuzak.driving_management.domain.model.value.PaymentMethod
import app.imuuzak.driving_management.domain.model.value.Schedule

/**
 * 走行会
 */
data class TrackEvent(
    // 開催サーキット
    val circuit: Circuit,
    // 主催者
    val organizer: Organizer,
    // 持ち物
    val belongings: List<Belonging>,
    // 集合時間
    val meetingTime: Schedule,
    // 解散時間
    val dismissalTime: Schedule?,
    // 注意事項
    val precautions: String,
    // 料金
    val price: Int,
    // 料金税込
    val priceWithTax: Int,
    // 支払い方法
    val paymentMethod: PaymentMethod,
    // 支払い期限
    val paymentDeadline: Schedule?
)