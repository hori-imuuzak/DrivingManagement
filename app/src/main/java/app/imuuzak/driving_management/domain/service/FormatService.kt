package app.imuuzak.driving_management.domain.service

import app.imuuzak.driving_management.domain.model.value.Time
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

class FormatService {
    companion object {
        private val df = SimpleDateFormat("yyyy/MM/dd", Locale.JAPAN)
        private val tf = SimpleDateFormat("hh:mm", Locale.JAPAN)
        private val nf = DecimalFormat("#,###")

        fun dateFormat(d: Date) = df.format(d)
        fun dateRangeFormat(d1: Date, d2: Date) = "${df.format(d1)} ~ ${df.format(d2)}"
        fun timeFormat(d: Date) = tf.format(d)
        fun timeRangeFormat(t1: Date, t2: Date) = "${timeFormat(t1)} ~ ${timeFormat(t2)}"
        fun timeRangeFormat(t1: Time, t2: Time) = "${t1.formatText()} ~ ${t2.formatText()}"
        fun priceFormat(p: Int) = "¥${nf.format(p)}"
    }
}