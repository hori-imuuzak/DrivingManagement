package app.imuuzak.driving_management.ui.circuit.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CreateCircuitViewModel : ViewModel() {
    val name = MutableLiveData<String>()
    val nameKana = MutableLiveData<String>()
    val url = MutableLiveData<String>()
}