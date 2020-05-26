package app.imuuzak.driving_management.ui.home.viewmodel

import android.app.Application
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.*
import app.imuuzak.driving_management.R
import app.imuuzak.driving_management.domain.model.TrackEvent
import app.imuuzak.driving_management.domain.model.Weather
import app.imuuzak.driving_management.domain.model.value.Pagination
import app.imuuzak.driving_management.domain.model.value.PaymentStatus
import app.imuuzak.driving_management.domain.model.value.WeatherType
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

    private var _weatherList =
        MutableLiveData<Array<Weather?>>()

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

    fun weatherDrawable(position: Int) = Transformations.map(_weatherList) {
        when (it[position]?.type) {
            WeatherType.CLEAR -> ContextCompat.getDrawable(app, R.drawable.clear)
            WeatherType.SUNNY -> ContextCompat.getDrawable(app, R.drawable.sunny)
            WeatherType.CLOUDY -> ContextCompat.getDrawable(app, R.drawable.cloudy)
            WeatherType.RAINY -> ContextCompat.getDrawable(app, R.drawable.rainy)
            WeatherType.FOG -> ContextCompat.getDrawable(app, R.drawable.fog)
            WeatherType.STORM -> ContextCompat.getDrawable(app, R.drawable.storm)
            WeatherType.SNOW -> ContextCompat.getDrawable(app, R.drawable.snow)
            else -> ContextCompat.getDrawable(app, android.R.drawable.screen_background_dark)
        }
    }

    fun weatherText(position: Int) = Transformations.map(_weatherList) {
        when (it[position]?.type) {
            WeatherType.CLEAR -> app.getString(R.string.weather_clear)
            WeatherType.SUNNY -> app.getString(R.string.weather_sunny)
            WeatherType.CLOUDY -> app.getString(R.string.weather_cloudy)
            WeatherType.RAINY -> app.getString(R.string.weather_rainy)
            WeatherType.FOG -> app.getString(R.string.weather_fog)
            WeatherType.STORM -> app.getString(R.string.weather_storm)
            WeatherType.SNOW -> app.getString(R.string.weather_snow)
            else -> ""
        }
    }

    fun maxTemperatureText(position: Int) = Transformations.map(_weatherList) {
        app.getString(R.string.format_temperature, it[position]?.maxTemp?.toString() ?: "")
    }

    fun minTemperatureText(position: Int) = Transformations.map(_weatherList) {
        app.getString(R.string.format_temperature, it[position]?.minTemp?.toString() ?: "")
    }

    fun getTrackEventList() {
        viewModelScope.launch {
            val trackEventList = trackEventRepository.get(Pagination(page = 1, perPage = 100))
            _trackEventList.value = trackEventList
            _weatherList.value = arrayOfNulls(trackEventList.size)
        }
    }

    fun getWeather(position: Int) {
        viewModelScope.launch {
            try {
                val weather = weatherRepository.getByZipCode("252-0804")
                _weatherList.value?.let { it[position] = weather }
                _weatherList.value = _weatherList.value
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