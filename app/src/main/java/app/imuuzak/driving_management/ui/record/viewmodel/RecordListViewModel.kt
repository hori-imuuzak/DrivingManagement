package app.imuuzak.driving_management.ui.record.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.imuuzak.driving_management.domain.model.Circuit
import app.imuuzak.driving_management.domain.model.Record
import app.imuuzak.driving_management.domain.repository.RecordRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class RecordListViewModel @Inject constructor(
    private val recordRepository: RecordRepository
) : ViewModel() {
    private var _recordList = MutableLiveData<List<Record>>()
    val recordList: LiveData<List<Record>> = _recordList

    fun loadRecord(circuit: Circuit) {
        viewModelScope.launch {
            _recordList.value = recordRepository.getRecord(circuit)
        }
    }
}