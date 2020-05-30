package app.imuuzak.driving_management.ui.home.viewmodel

import androidx.lifecycle.*
import app.imuuzak.driving_management.domain.model.Circuit
import app.imuuzak.driving_management.domain.model.Record
import app.imuuzak.driving_management.domain.repository.CircuitRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class RecordViewModel @Inject constructor(
    private val circuitRepository: CircuitRepository
) : ViewModel() {
    private var _circuitList = MutableLiveData<List<Circuit>>()
    val circuitList: LiveData<List<Circuit>> = _circuitList
    val circuitNameList = Transformations.map(_circuitList) { it.map { circuit -> circuit.name }}

    fun loadHasRecordCircuitList() {
        viewModelScope.launch {
            _circuitList.value = circuitRepository.getHasRecord()
        }
    }

    private var _selectCircuit = MutableLiveData<Circuit?>().apply { value = null }
    val selectCircuit: LiveData<Circuit?> = _selectCircuit

    fun setSelectCircuit(circuit: Circuit?) {
        _selectCircuit.value = circuit
    }

    private var _recordList = MutableLiveData<List<Record>>()
    val recordList: LiveData<List<Record>> = _recordList

    fun loadRecord(circuit: Circuit) {

    }
}