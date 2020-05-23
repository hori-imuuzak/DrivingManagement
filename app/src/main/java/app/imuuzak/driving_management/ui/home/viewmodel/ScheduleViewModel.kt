package app.imuuzak.driving_management.ui.home.viewmodel

import android.app.Application
import androidx.lifecycle.*
import app.imuuzak.driving_management.R
import app.imuuzak.driving_management.domain.model.TrackEvent
import app.imuuzak.driving_management.domain.model.value.Pagination
import app.imuuzak.driving_management.domain.model.value.PaymentStatus
import app.imuuzak.driving_management.domain.repository.TrackEventRepository
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class ScheduleViewModel @Inject constructor(
    val app: Application,
    val trackEventRepository: TrackEventRepository
) : AndroidViewModel(app) {
    private var _trackEventList = MutableLiveData<List<TrackEvent>>()
    val trackEventList: LiveData<List<TrackEvent>> = _trackEventList

    fun dateText(position: Int): LiveData<String> = Transformations.map(_trackEventList) {
        val event = it[position]
        var text = ""
        event.date?.let { schedule ->
            if (schedule.begin?.equals(schedule.end) == true && schedule.end != null) {
                text = "${df.format(schedule.begin)} ${tf.format(schedule.begin)}~${tf.format(schedule.end)}"
            } else if (schedule.begin != null && schedule.end != null) {
                text = "${df.format(schedule.begin)}~${df.format(schedule.end)}"
            }
        }

        text
    }

    fun placeText(position: Int) =
        Transformations.map(_trackEventList) { it[position].circuit?.name ?: "" }

    fun organizerText(position: Int) =
        Transformations.map(_trackEventList) { it[position].organizer?.name ?: "" }

    fun hasPrecaution(position: Int) =
        Transformations.map(_trackEventList) { it[position].precautions?.isNotEmpty() }

    fun paymentStatusText(position: Int) =
        Transformations.map(_trackEventList) {
            when (it[position].paymentStatus) {
                PaymentStatus.YET -> app.getString(R.string.payment_status_yet)
                PaymentStatus.DONE -> app.getString(R.string.payment_status_done)
                PaymentStatus.EXPIRED -> app.getString(R.string.payment_status_expired)
                else -> ""
            }
        }

    fun paymentDeadlineText(position: Int) =
        Transformations.map(_trackEventList) {
            it[position].paymentDeadline?.begin?.let { date ->
                df.format(date)
            } ?: ""
        }


    fun getTrackEventList() {
        viewModelScope.launch {
            _trackEventList.value = trackEventRepository.get(Pagination(page = 1, perPage = 100))
        }
    }

    private val df = SimpleDateFormat("yyyy/MM/dd", Locale.JAPAN)
    private val tf = SimpleDateFormat("hh:mm", Locale.JAPAN)
}