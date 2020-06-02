package app.imuuzak.driving_management.ui.record.viewmodel

import android.app.Application
import androidx.lifecycle.*
import app.imuuzak.driving_management.R
import app.imuuzak.driving_management.domain.model.Circuit
import app.imuuzak.driving_management.domain.model.Record
import app.imuuzak.driving_management.domain.repository.RecordRepository
import app.imuuzak.driving_management.domain.service.FormatService
import kotlinx.coroutines.launch
import javax.inject.Inject

class RecordListViewModel @Inject constructor(
    private val app: Application,
    private val recordRepository: RecordRepository
) : AndroidViewModel(app) {
    private var _recordList = MutableLiveData<List<Record>>()
    val recordList: LiveData<List<Record>> = _recordList

    fun loadRecord(circuit: Circuit) {
        viewModelScope.launch {
            _recordList.value = recordRepository.getRecord(circuit)
        }
    }

    val dateText = Transformations.map(_recordList) {
        it.map { record -> FormatService.dateFormat(record.date) }
    }

    val recordCountText = Transformations.map(_recordList) {
        it.map { record -> app.getString(R.string.record_count, record.recordList.size) }
    }

    val fastestTimeText = Transformations.map(_recordList) {
        it.map { record -> app.getString(R.string.fastest_laptime, record.recordList.maxBy { time -> time.seconds() }?.format()) }
    }

    val memoText = Transformations.map(_recordList) {
        it.map { record -> app.getString(R.string.memo_text, record.memo) }
    }
}