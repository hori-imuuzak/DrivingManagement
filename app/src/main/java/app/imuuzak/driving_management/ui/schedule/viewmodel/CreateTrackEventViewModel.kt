package app.imuuzak.driving_management.ui.schedule.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import app.imuuzak.driving_management.domain.model.value.Time
import java.text.SimpleDateFormat
import java.util.*

class CreateTrackEventViewModel : ViewModel() {
    private val meetingDate = MutableLiveData<Date>().apply {
        value = Date()
    }
    val meetingDateText: LiveData<String> = Transformations.map(meetingDate) { df.format(it) }
    fun setMeetingDate(year: Int, month: Int, day: Int) {
        meetingDate.value = getDate(year, month, day)
    }

    private val meetingTime = MutableLiveData<Time>().apply {
        value = Time()
    }
    val meetingTimeText: LiveData<String> = Transformations.map(meetingTime) { it.formatText() }
    fun setMeetingTime(hour: Int, minute: Int) {
        meetingTime.value = Time(hour, minute)
    }

    private val dismissalTime = MutableLiveData<Time>().apply { value = Time() }
    val dismissalTimeText: LiveData<String> = Transformations.map(dismissalTime) { it.formatText() }
    fun setDismissalTime(hour: Int, minute: Int) {
        dismissalTime.value = Time(hour, minute)
    }

    val precautions = MutableLiveData<String>()

    val price = MutableLiveData<String>().apply { value = "" }
    val priceWithTax: LiveData<String> = Transformations.map(price) {
        "${((it.toIntOrNull() ?: 0) * 1.08).toInt()}"
    }

    private val paymentDeadline = MutableLiveData<Date>().apply { value = Date() }
    val paymentDeadlineText: LiveData<String> =
        Transformations.map(paymentDeadline) { df.format(it) }

    fun setPaymentDeadline(year: Int, month: Int, day: Int) {
        paymentDeadline.value = getDate(year, month, day)
    }

    private val df = SimpleDateFormat("yyyy/MM/dd", Locale.JAPAN)
    private fun getDate(year: Int, month: Int, day: Int): Date {
        return Calendar.getInstance().apply {
            set(Calendar.YEAR, year)
            set(Calendar.MONTH, month)
            set(Calendar.DAY_OF_MONTH, day)
        }.time
    }
}