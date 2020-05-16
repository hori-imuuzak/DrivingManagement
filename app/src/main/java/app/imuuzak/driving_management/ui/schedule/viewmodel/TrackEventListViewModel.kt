package app.imuuzak.driving_management.ui.schedule.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.imuuzak.driving_management.domain.model.TrackEvent

class TrackEventListViewModel: ViewModel() {
    private val _trackEventList = MutableLiveData<List<TrackEvent>>()
    val trackEventList: LiveData<List<TrackEvent>>
        get() = _trackEventList

    fun loadTrackEventList() {
        // TODO load from repository
    }
}