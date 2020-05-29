package app.imuuzak.driving_management.ui.schedule.viewmodel

import android.app.Application
import androidx.lifecycle.*
import app.imuuzak.driving_management.R
import app.imuuzak.driving_management.domain.model.TrackEvent
import app.imuuzak.driving_management.domain.model.value.PaymentStatus
import java.util.*
import javax.inject.Inject

class ScheduleDetailViewModel @Inject constructor(val app: Application) : AndroidViewModel(app) {
    private var _trackEvent = MutableLiveData<TrackEvent>()

    fun setTrackEvent(trackEvent: TrackEvent) {
        _trackEvent.value = trackEvent

        checkedPaymentStatus.value = when (trackEvent.paymentStatus) {
            PaymentStatus.YET -> R.id.payment_status_yet
            PaymentStatus.DONE -> R.id.payment_status_done
            else -> null
        }
    }

    val circuitName = Transformations.map(_trackEvent) { it.circuit?.name ?: "" }
    val organizerName = Transformations.map(_trackEvent) { it.organizer?.name ?: "" }
    val scheduleDateText = Transformations.map(_trackEvent) {
        it.date?.beginText()
    }
    val scheduleTimeText = Transformations.map(_trackEvent) {
        it.scheduleTimeText()
    }
    val belongingTextList = Transformations.map(_trackEvent) {
        it.belongings.map { belonging -> belonging.name }
    }
    val precautionText = Transformations.map(_trackEvent) {
        it.precautions ?: ""
    }
    val priceText = Transformations.map(_trackEvent) {
        it.priceText()
    }
    val paymentMethodText = Transformations.map(_trackEvent) {
        it.paymentMethod?.textResId()?.let { resId ->
            app.getString(resId)
        } ?: ""
    }
    val isExpired = Transformations.map(_trackEvent) {
        it.paymentStatus == PaymentStatus.YET && it.paymentDeadline != null && it.paymentDeadline?.begin?.before(
            Date()
        ) == true
    }
    val checkedPaymentStatus = MutableLiveData<Int>()
    val deadlineText = Transformations.map(_trackEvent) {
        app.getString(R.string.text_limit, it.paymentDeadline?.beginText())
    }
}