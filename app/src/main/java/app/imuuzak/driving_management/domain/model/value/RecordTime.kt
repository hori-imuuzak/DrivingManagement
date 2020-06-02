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

    fun seconds(): Double {
        return format().toDoubleOrNull() ?: 0.0
    }

    companion object {
        fun fromString(value: String): RecordTime {
            val s1 = value.split(".")
            if (s1.size != 2) throw IllegalArgumentException("invalid format recordTime")

            var seconds = 0
            if (s1[0].indexOf(":") < 0) {
                // 秒数のみであれば数字変換できるかどうか
                seconds = s1[0].toIntOrNull() ?: throw IllegalArgumentException("invalid format recordTime seconds")
            } else {
                val s2 = s1[0].split(":")
                when (s2.size) {
                    2 -> {
                        // 分:秒
                        val m = s2[0].toIntOrNull()
                        val s = s2[1].toIntOrNull()
                        if (m == null || s == null) throw IllegalArgumentException("invalid format recordTime seconds")
                        seconds = m * 60 + s
                    }
                    3 -> {
                        // 時:分:秒
                        val h = s2[0].toIntOrNull()
                        val m = s2[1].toIntOrNull()
                        val s = s2[2].toIntOrNull()
                        if (h == null || m == null || s == null) throw IllegalArgumentException("invalid format recordTime seconds")
                        seconds = h * 3600 + m * 60 + s
                    }
                    else -> {
                        throw IllegalArgumentException("invalid format recordTime seconds")
                    }
                }
            }

            val decimal = s1[1].toIntOrNull()
                ?: throw IllegalArgumentException("invalid format recordTime decimal")

            return RecordTime(
                seconds = seconds,
                decimal = decimal
            )
        }
    }
}