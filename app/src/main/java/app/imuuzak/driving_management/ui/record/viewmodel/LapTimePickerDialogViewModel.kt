package app.imuuzak.driving_management.ui.record.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import app.imuuzak.driving_management.domain.model.value.RecordTime

class LapTimePickerDialogViewModel : ViewModel() {
    private val _minutes = MutableLiveData<Int>().apply { value = 0 }
    val minutes: LiveData<Int> = _minutes
    fun setMinutes(value: Int) {
        _minutes.value = value
    }

    private val _seconds = MutableLiveData<Int>().apply { value = 0 }
    val seconds: LiveData<Int> = _seconds
    fun setSeconds(value: Int) {
        _seconds.value = value
    }

    private val _millSec = MutableLiveData<Int>().apply { value = 0 }
    val millSec: LiveData<Int> = _millSec
    fun setMillSec(value: Int) {
        _millSec.value = value
    }

    private val _microSec = MutableLiveData<Int>().apply { value = 0 }
    val microSec: LiveData<Int> = _microSec
    fun setMicroSec(value: Int) {
        _microSec.value = value
    }

    private val _nanoSec = MutableLiveData<Int>().apply { value = 0 }
    val nanoSec: LiveData<Int> = _nanoSec
    fun setNanoSec(value: Int) {
        _nanoSec.value = value
    }

    private val _recordTime = MutableLiveData<RecordTime>()
    val recordTime: LiveData<RecordTime> = _recordTime
    fun addRecord() {
        val totalSeconds = (_minutes.value ?: 0) * 60 + (_seconds.value ?: 0)
        val decimal = (_millSec.value ?: 0) * 100 + (_microSec.value ?: 0) * 10 + (_nanoSec.value ?: 0)
        _recordTime.value = RecordTime(
            seconds = totalSeconds,
            decimal = decimal
        )
    }

    val canAddLapTime = MediatorLiveData<Boolean>().apply {
        val pickObserver = Observer<Int> {
            value = (_minutes.value ?: 0) + (_seconds.value ?: 0) + (_millSec.value ?: 0) + (_microSec.value ?: 0) + (_nanoSec.value ?: 0) > 0
        }
        addSource(_minutes, pickObserver)
        addSource(_seconds, pickObserver)
        addSource(_millSec, pickObserver)
        addSource(_microSec, pickObserver)
        addSource(_nanoSec, pickObserver)
    }
}