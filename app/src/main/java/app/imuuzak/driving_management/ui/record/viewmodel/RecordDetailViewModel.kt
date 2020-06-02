package app.imuuzak.driving_management.ui.record.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import app.imuuzak.driving_management.domain.model.Record
import app.imuuzak.driving_management.domain.service.FormatService

class RecordDetailViewModel : ViewModel() {
    private val _record = MutableLiveData<Record>()
    fun setRecord(record: Record) {
        _record.value = record
    }

    val circuitName = Transformations.map(_record) { it.circuit.name }

    val dateText = Transformations.map(_record) { FormatService.dateFormat(it.date) }

    val memoText = Transformations.map(_record) { it.memo }
}