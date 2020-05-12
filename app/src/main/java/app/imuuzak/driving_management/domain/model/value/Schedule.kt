package app.imuuzak.driving_management.domain.model.value

import java.util.*

/**
 * スケジュール
 */
data class Schedule(
    // 開始日時
    val begin: Date,
    // 終了日時
    val end: Date
) {
    init {
        if (begin.after(end)) {
            throw IllegalArgumentException("BEGIN must be a date prior to END")
        }
    }
}