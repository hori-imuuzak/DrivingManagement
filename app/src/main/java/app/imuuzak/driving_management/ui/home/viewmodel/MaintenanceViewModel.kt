package app.imuuzak.driving_management.ui.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MaintenanceViewModel : ViewModel() {
    private val _onClickCreateCar = MutableLiveData<Boolean>()
    val onClickCreateCar: LiveData<Boolean> = _onClickCreateCar

    fun onClickCreateCar() {
        this._onClickCreateCar.value = true
    }
}