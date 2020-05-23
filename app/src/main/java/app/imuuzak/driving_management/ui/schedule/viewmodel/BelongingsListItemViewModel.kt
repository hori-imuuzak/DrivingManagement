package app.imuuzak.driving_management.ui.schedule.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.imuuzak.driving_management.domain.model.Belonging

class BelongingsListItemViewModel: ViewModel() {
    var belonging = MutableLiveData<Belonging?>().apply {
        value = null
    }
    fun setBelonging(belonging: Belonging) {
        this.belonging.value = belonging
    }
    
    var uiEvent: UIEvent? = null

    var name = this.belonging.value?.name

    interface UIEvent {
        fun onClickRemove()
    }
}