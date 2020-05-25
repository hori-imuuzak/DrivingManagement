package app.imuuzak.driving_management.ui.home.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import app.imuuzak.driving_management.R
import app.imuuzak.driving_management.domain.model.TrackEvent
import app.imuuzak.driving_management.domain.model.value.Pagination
import app.imuuzak.driving_management.domain.model.value.PaymentStatus
import app.imuuzak.driving_management.domain.repository.TrackEventRepository
import app.imuuzak.driving_management.domain.repository.WeatherRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class ScheduleViewModel @Inject constructor(
    val app: Application,
    val trackEventRepository: TrackEventRepository,
    val weatherRepository: WeatherRepository
) : AndroidViewModel(app) {
    private var _trackEventList = MutableLiveData<List<TrackEvent>>()
    val trackEventList: LiveData<List<TrackEvent>> = _trackEventList

    fun dateText(position: Int): LiveData<String> = Transformations.map(_trackEventList) {
        val event = it[position]
        event.date?.dateRangeText()
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
            it[position].paymentDeadline?.beginText()
        }

    fun trackEventAt(position: Int) = Transformations.map(_trackEventList) {
        it[position]
    }

    fun getTrackEventList() {
        viewModelScope.launch {
            _trackEventList.value = trackEventRepository.get(Pagination(page = 1, perPage = 100))
        }
    }

    fun getWeather(position: Int) {
        viewModelScope.launch {
            try {
                val weather = weatherRepository.getByZipCode("252-0804")
                Log.d("WEATHER", weather.zipCode ?: "")
            } catch (e: Exception) {
                // TODO
            }
        }
    }

    var uiEvent: UIEvent? = null
    interface UIEvent {
        fun onClickSchedule(position: Int)
    }
}