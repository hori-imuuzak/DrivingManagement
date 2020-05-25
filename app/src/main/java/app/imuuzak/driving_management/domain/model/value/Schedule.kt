package app.imuuzak.driving_management.domain.model.value

import app.imuuzak.driving_management.domain.service.FormatService
import java.io.Serializable
import java.util.*

/**
 * スケジュール
 */
data class Schedule(
    // 開始日時
    val begin: Date? = Date(),
    // 終了日時
    val end: Date? = Date()
) : Serializable {
    init {
        if (begin == null) {
            throw IllegalArgumentException("begin")
        }

        if (end == null) {
            throw IllegalArgumentException("begin")
        }

        if (begin.after(end)) {
            throw IllegalArgumentException("BEGIN must be a date prior to END")
        }
    }

    fun isSameBeginEnd() = begin?.equals(end) == true && end != null

    fun dateRangeText(): String {
        return if (isSameBeginEnd()) {
            // isSameBeginEndがtrueであればbegin/endはnullではない
            "${FormatService.dateFormat(begin!!)} ${FormatService.timeRangeFormat(begin, end!!)}"
        } else if (begin != null && end != null) {
            FormatService.dateRangeFormat(begin, end)
        } else {
            ""
        }
    }

    fun beginText(): String {
        return begin?.let { FormatService.dateFormat(it) } ?: ""
    }

    fun endText(): String {
        return end?.let { FormatService.dateFormat(it) } ?: ""
    }
}