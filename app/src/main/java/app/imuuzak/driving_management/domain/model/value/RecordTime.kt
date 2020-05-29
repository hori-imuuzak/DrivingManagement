package app.imuuzak.driving_management.domain.model.value

data class RecordTime(
    val seconds: Int,   // 秒数
    val decimal: Int    // 小数以下（別途整数として扱う）
) {
    fun format(): String {
        val h = seconds / 3600
        val m = seconds / 60
        val s = seconds % 60
        val ms = decimal

        var str = ""
        if (h > 0) str += "${"%02d".format(h)}:"
        if (m > 0) str += "${"%02d".format(m)}:"
        str += "${"%02d".format(s)}.${ms}"

        return str
    }
}