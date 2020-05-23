package app.imuuzak.driving_management.ui.schedule.viewmodel

import android.app.Application
import androidx.lifecycle.*
import androidx.lifecycle.Observer
import app.imuuzak.driving_management.R
import app.imuuzak.driving_management.domain.model.Belonging
import app.imuuzak.driving_management.domain.model.Circuit
import app.imuuzak.driving_management.domain.model.Organizer
import app.imuuzak.driving_management.domain.model.TrackEvent
import app.imuuzak.driving_management.domain.model.value.PaymentMethod
import app.imuuzak.driving_management.domain.model.value.Schedule
import app.imuuzak.driving_management.domain.model.value.Time
import app.imuuzak.driving_management.domain.repository.CircuitRepository
import app.imuuzak.driving_management.domain.repository.OrganizerRepository
import app.imuuzak.driving_management.domain.repository.ResourceState
import app.imuuzak.driving_management.domain.repository.TrackEventRepository
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class CreateTrackEventViewModel @Inject constructor(
    private val app: Application,
    private val circuitRepository: CircuitRepository,
    private val organizerRepository: OrganizerRepository,
    private val trackEventRepository: TrackEventRepository
) :
    AndroidViewModel(app) {
    // サーキット選択
    private val _circuitList = MutableLiveData<List<Circuit>>()
    val circuitNameList: LiveData<List<String>> =
        Transformations.map(_circuitList) { it.map { circuit -> circuit.name } }

    fun setCircuitList(circuitList: List<Circuit>) {
        _circuitList.value = circuitList
    }

    fun loadCircuitList(): LiveData<List<Circuit>> {
        val data = MutableLiveData<List<Circuit>>().apply {
            value = listOf()
        }

        viewModelScope.launch {
            val circuitList = circuitRepository.getAll()
            data.value = circuitList
        }

        return data
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

    // 主催者選択
    private val _organizerList = MutableLiveData<List<Organizer>>()
    val organizerNameList: LiveData<List<String>> =
        Transformations.map(_organizerList) { it.map { organizer -> organizer.name } }

    fun setOrganizerList(organizerList: List<Organizer>) {
        _organizerList.value = organizerList
    }

    fun loadOrganizerList(): LiveData<List<Organizer>> {
        val data = MutableLiveData<List<Organizer>>().apply {
            value = listOf()
        }

        viewModelScope.launch {
            val organizerList = organizerRepository.getAll()
            data.value = organizerList
        }

        return data
    }

    // 選択中主催者
    private val _selectedOrganizer = MutableLiveData<Organizer>()
    val selectedOrganizerPosition = MediatorLiveData<Int>().apply {
        addSource(_organizerList, Observer {
            value = if (_organizerList.value?.isNotEmpty() == true) {
                it.indexOf(_selectedOrganizer.value)
            } else {
                0
            }
        })
        addSource(_selectedOrganizer, Observer {
            _organizerList.value?.let {
                if (it.isNotEmpty()) {
                    value = it.indexOf(_selectedOrganizer.value)
                }
            }
        })
    }

    fun selectOrganizerAt(position: Int) {
        _organizerList.value?.let {
            _selectedOrganizer.value = it[position]
        }
    }

    // 持ち物リスト
    var belongingNames = MutableLiveData<MutableList<String>>().apply { value = mutableListOf() }

    fun addBelonging(belonging: Belonging): Int {
        belongingNames.value?.add("")

        belongingNames.value = belongingNames.value

        return belongingNames.value?.size ?: 0
    }

    fun removeBelonging(position: Int): Int {
        belongingNames.value?.removeAt(position)

        belongingNames.value = belongingNames.value

        return belongingNames.value?.size ?: 0
    }

    val belongingsCount = Transformations.map(belongingNames) {
        it.size
    }

    // 開催日
    private val meetingDate = MutableLiveData<Date>().apply {
        value = Date()
    }
    val meetingDateText: LiveData<String> = Transformations.map(meetingDate) { df.format(it) }
    fun setMeetingDate(year: Int, month: Int, day: Int) {
        meetingDate.value = getDate(year, month, day)
    }

    // 開催時間
    private val meetingTime = MutableLiveData<Time>().apply {
        value = Time()
    }
    val meetingTimeText: LiveData<String> = Transformations.map(meetingTime) { it.formatText() }
    fun setMeetingTime(hour: Int, minute: Int) {
        meetingTime.value = Time(hour, minute)
    }

    // 解散時間
    private val dismissalTime = MutableLiveData<Time>().apply { value = Time() }
    val dismissalTimeText: LiveData<String> = Transformations.map(dismissalTime) { it.formatText() }
    fun setDismissalTime(hour: Int, minute: Int) {
        dismissalTime.value = Time(hour, minute)
    }

    // 注意事項
    val precautions = MutableLiveData<String>()

    // 価格
    val price = MutableLiveData<String>().apply { value = "" }

    // 選択中支払い方法
    private val paymentMethodList = listOf(
        PaymentMethod.CARD,
        PaymentMethod.BANK,
        PaymentMethod.CONVENIENCE_STORE,
        PaymentMethod.PAYMENT_SERVICE,
        PaymentMethod.LOCAL,
        PaymentMethod.OTHER
    )
    val paymentMethodTextList = listOf(
        app.getString(R.string.payment_method_card),
        app.getString(R.string.payment_method_bank),
        app.getString(R.string.payment_method_convenience_store),
        app.getString(R.string.payment_method_payment_service),
        app.getString(R.string.payment_method_local),
        app.getString(R.string.payment_method_other)
    )
    private val _selectedPaymentMethod = MutableLiveData<PaymentMethod>()
    fun selectPaymentMethodAt(position: Int) {
        _selectedPaymentMethod.value = paymentMethodList[position]
    }

    val selectedPaymentMethodPosition = Transformations.map(_selectedPaymentMethod) {
        paymentMethodList.indexOf(it)
    }

    // 支払い期限
    private val paymentDeadline = MutableLiveData<Date>().apply { value = Date() }
    val paymentDeadlineText: LiveData<String> =
        Transformations.map(paymentDeadline) { df.format(it) }

    fun setPaymentDeadline(year: Int, month: Int, day: Int) {
        paymentDeadline.value = getDate(year, month, day)
    }

    // 走行会イベント作成
    private val _createdTrackEventResource = MutableLiveData<ResourceState<TrackEvent>>()
    val createdTrackEventResource = _createdTrackEventResource

    fun createTrackEvent() {
        viewModelScope.launch {
            _createdTrackEventResource.value = ResourceState.loading()

            try {
                val trackEvent = TrackEvent(
                    circuit = _selectedCircuit.value,
                    organizer = _selectedOrganizer.value,
                    belongings = belongingNames.value?.map {
                        Belonging(it, 1)
                    } ?: listOf(),
                    date = Schedule(
                        begin = meetingDate.value,
                        end = meetingDate.value
                    ),
                    meetingTime = meetingTime.value,
                    dismissalTime = dismissalTime.value,
                    precautions = precautions.value,
                    price = price.value?.toIntOrNull(),
                    paymentMethod = _selectedPaymentMethod.value,
                    paymentDeadline = Schedule(
                        begin = paymentDeadline.value,
                        end = paymentDeadline.value
                    )
                )

                val createdTrack = trackEventRepository.create(trackEvent)
                _createdTrackEventResource.value = ResourceState.success(createdTrack)
            } catch (e: IllegalArgumentException) {
                _createdTrackEventResource.value = ResourceState.error(e.message)
            }
        }
    }

    private val df = SimpleDateFormat("yyyy/MM/dd", Locale.JAPAN)
    private fun getDate(year: Int, month: Int, day: Int): Date {
        return Calendar.getInstance().apply {
            set(Calendar.YEAR, year)
            set(Calendar.MONTH, month)
            set(Calendar.DAY_OF_MONTH, day)
        }.time
    }
}