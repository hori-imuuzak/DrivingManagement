package app.imuuzak.driving_management.ui.schedule.viewmodel

import android.util.Log
import androidx.lifecycle.*
import androidx.lifecycle.Observer
import app.imuuzak.driving_management.domain.model.Circuit
import app.imuuzak.driving_management.domain.model.value.Time
import app.imuuzak.driving_management.domain.repository.CircuitRepository
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class CreateTrackEventViewModel @Inject constructor(private val circuitRepository: CircuitRepository) :
    ViewModel() {
    private val _circuitList = MutableLiveData<List<Circuit>>()
    val circuitNameList: LiveData<List<String>> =
        Transformations.map(_circuitList) { it.map { circuit -> circuit.name } }

    fun setCircuitList(circuitList: List<Circuit>) {
        _circuitList.value = circuitList
    }

    fun loadCircuitList(): LiveData<List<Circuit>> {
        val data = MutableLiveData<List<Circuit>>().apply {
            value = listOf()
        }

        viewModelScope.launch {
            val circuitList = circuitRepository.getAll()
            data.value = circuitList
        }

        return data
    }

    private val _selectedCircuit = MutableLiveData<Circuit>()
    val selectedCircuitPosition = MediatorLiveData<Int>().apply {
        addSource(_circuitList, Observer {
            value = if (_circuitList.value?.isNotEmpty() == true) {
                it.indexOf(_selectedCircuit.value)
            } else {
                0
            }
        })
        addSource(_selectedCircuit, Observer {
            _circuitList.value?.let {
                if (it.isNotEmpty()) {
                    value = it.indexOf(_selectedCircuit.value)
                }
            }
        })
    }

    fun selectCircuitAt(position: Int) {
        _circuitList.value?.let {
            _selectedCircuit.value = it[position]
        }
    }

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