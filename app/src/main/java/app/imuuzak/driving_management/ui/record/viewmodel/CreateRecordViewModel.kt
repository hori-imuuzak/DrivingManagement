package app.imuuzak.driving_management.ui.record.viewmodel

import android.app.Application
import androidx.lifecycle.*
import androidx.lifecycle.Observer
import app.imuuzak.driving_management.R
import app.imuuzak.driving_management.domain.model.Circuit
import app.imuuzak.driving_management.domain.model.Record
import app.imuuzak.driving_management.domain.model.value.RecordTime
import app.imuuzak.driving_management.domain.repository.CircuitRepository
import app.imuuzak.driving_management.domain.repository.RecordRepository
import app.imuuzak.driving_management.domain.repository.ResourceState
import app.imuuzak.driving_management.domain.service.FormatService
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class CreateRecordViewModel @Inject constructor(
    private val app: Application,
    private val circuitRepository: CircuitRepository,
    private val recordRepository: RecordRepository
) : AndroidViewModel(app) {
    // 日にち
    private val _date = MutableLiveData<Date?>().apply { value = null }
    val dateText: LiveData<String> = Transformations.map(_date) {
        it?.let { FormatService.dateFormat(it) } ?: app.getString(R.string.pick_date)
    }

    fun setDate(date: Date) {
        _date.value = date
    }

    // サーキット選択
    private val _circuitList = MutableLiveData<List<Circuit>>()
    val circuitNameList: LiveData<List<String>> =
        Transformations.map(_circuitList) { it.map { circuit -> circuit.name } }

    fun loadCircuitList() {
        viewModelScope.launch {
            val circuitList = circuitRepository.getAll()
            _circuitList.value = circuitList
        }
    }

    // 選択中サーキット
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

    private val _recordTimeList = MutableLiveData<MutableList<RecordTime>>().apply { value = mutableListOf() }
    val recordTimeList: LiveData<MutableList<RecordTime>> = _recordTimeList
    val recordTimeTextList = Transformations.map(_recordTimeList) { it.map { record -> record.format() } }

    fun addRecordTime(recordTime: RecordTime) {
        _recordTimeList.value?.add(recordTime)?.run {
            _recordTimeList.value = _recordTimeList.value
        }
    }

    fun removeRecordTime(position: Int) {
        _recordTimeList.value?.removeAt(position)?.run {
            _recordTimeList.value = _recordTimeList.value
        }
    }

    // メモ
    val memo = MutableLiveData<String>()

    // TODO photo

    fun createRecord(): LiveData<ResourceState<Record>> {
        val resource = MutableLiveData<ResourceState<Record>>()

        resource.value = ResourceState.loading()
        viewModelScope.launch {
            if (_selectedCircuit.value != null && _recordTimeList.value != null) {
                val record = Record(
                    circuit = _selectedCircuit.value!!,
                    date = Date(),
                    recordList = _recordTimeList.value!!,
                    memo = memo.value ?: "",
                    pictureUrlList = listOf()
                )
                recordRepository.createRecord(record)?.let {
                    resource.value = ResourceState.success(record)
                } ?: run {
                    resource.value = ResourceState.error("failure create")
                }
            } else {
                resource.value = ResourceState.error("invalid")
            }
        }

        return resource
    }
}