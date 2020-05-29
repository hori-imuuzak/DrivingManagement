package app.imuuzak.driving_management.domain.model

import app.imuuzak.driving_management.domain.model.value.RecordTime
import java.util.*

/**
 * 走行レコード
 */
data class Record(
    val circuit: Circuit,
    val date: Date,
    val recordList: List<RecordTime>,
    val memo: String = "",
    val pictureUrlList: List<String> = listOf()
) {
    init {
        if (recordList.isEmpty()) {
            throw IllegalArgumentException("recordList")
        }
    }
}