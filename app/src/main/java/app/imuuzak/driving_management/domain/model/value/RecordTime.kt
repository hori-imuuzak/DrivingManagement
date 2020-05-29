package app.imuuzak.driving_management.domain.model.value

data class RecordTime(
    val seconds: Int,   // 秒数
    val decimal: Int    // 小数以下
) {
    fun format(): String {
        val h = seconds / 3600
        val m = seconds / 60
        val s = seconds % 60
        val ms = decimal

        var str = ""
        if (h > 0) str += "${h}:"
        if (m > 0) str += "${m}:"
        str += "${s}.${ms}"

        return str
    }
}