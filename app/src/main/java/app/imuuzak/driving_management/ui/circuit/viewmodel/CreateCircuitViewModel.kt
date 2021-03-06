package app.imuuzak.driving_management.ui.circuit.viewmodel

import androidx.lifecycle.*
import app.imuuzak.driving_management.domain.model.Circuit
import app.imuuzak.driving_management.domain.repository.CircuitRepository
import app.imuuzak.driving_management.domain.repository.ResourceState
import kotlinx.coroutines.launch
import javax.inject.Inject

class CreateCircuitViewModel @Inject constructor(private val circuitRepository: CircuitRepository) : ViewModel() {
    val name = MutableLiveData<String>()
    val kana = MutableLiveData<String>()
    val url = MutableLiveData<String>()

    private val _createCircuit = MutableLiveData<ResourceState<Circuit>>()
    val circuitResource = _createCircuit

    fun createCircuit() {
        viewModelScope.launch {
            try {
                val circuit = Circuit(
                    name = name.value ?: "",
                    kana = kana.value ?: "",
                    url = url.value ?: ""
                )

                _createCircuit.value = ResourceState.loading()
                val createdData = circuitRepository.create(circuit)
                createdData?.let {
                    _createCircuit.value = ResourceState.success(circuit)
                } ?: run {
                    _createCircuit.value = ResourceState.error("failed createCircuit()")
                }
            } catch (e: IllegalArgumentException) {
                _createCircuit.value = ResourceState.error(e.message)
            }
        }
    }
}