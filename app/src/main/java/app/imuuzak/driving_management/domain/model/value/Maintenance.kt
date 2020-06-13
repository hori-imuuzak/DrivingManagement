package app.imuuzak.driving_management.domain.model.value

import java.util.*

/**
 * メンテナンス項目
 */
data class Maintenance(
    // 状態
    val status: MaintenanceStatusType,
    // 走行距離
    val odometer: Int,
    // 最終確認日
    val lastCheckDate: Date
)