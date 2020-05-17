package app.imuuzak.driving_management.ui.organizer.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.imuuzak.driving_management.R
import app.imuuzak.driving_management.domain.model.BankAccount
import app.imuuzak.driving_management.domain.model.Organizer
import app.imuuzak.driving_management.domain.model.value.AccountType
import app.imuuzak.driving_management.domain.repository.OrganizerRepository
import app.imuuzak.driving_management.domain.repository.ResourceState
import kotlinx.android.synthetic.main.activity_create_organizer.view.*
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException
import javax.inject.Inject

class CreateOrganizerViewModel @Inject constructor(private val organizerRepository: OrganizerRepository): ViewModel() {
    val name = MutableLiveData<String>()
    val kana = MutableLiveData<String>()
    val representativeName = MutableLiveData<String>()
    val phoneNumber = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val bankName = MutableLiveData<String>()
    val branchName = MutableLiveData<String>()
    val holderName = MutableLiveData<String>()
    val accountType = MutableLiveData<AccountType>()
    val accountNumber = MutableLiveData<String>()

    private val _createdOrganizerResource = MutableLiveData<ResourceState<Organizer>>()
    val createdOrganizerResource = _createdOrganizerResource

    fun onCheckedChangeAccountType(radioId: Int) {
        accountType.value = when (radioId) {
            R.id.radio_account_type_savings -> AccountType.SAVINGS_ACCOUNT
            R.id.radio_account_type_current -> AccountType.CURRENT_ACCOUNT
            else -> AccountType.SAVINGS_ACCOUNT
        }
    }

    fun createOrganizer() {
        viewModelScope.launch {
            try {
                val organizer = Organizer(
                    name = name.value ?: "",
                    kana = kana.value ?: "",
                    representativeName = representativeName.value ?: "",
                    phoneNumber = phoneNumber.value ?: "",
                    email = email.value ?: "",
                    bankAccount = listOf(
                        BankAccount(
                            bankCode = "",
                            bankName = bankName.value ?: "",
                            branchCode = "",
                            branchName = branchName.value ?: "",
                            holderName = holderName.value ?: "",
                            accountType = accountType.value ?: AccountType.SAVINGS_ACCOUNT,
                            accountNumber = accountNumber.value ?: ""
                        )
                    )
                )

                _createdOrganizerResource.value = ResourceState.loading()

                val createdData = organizerRepository.create(organizer)
                createdData?.let {
                    _createdOrganizerResource.value = ResourceState.success(it)
                } ?: run {
                    _createdOrganizerResource.value = ResourceState.error("failed createOrganizer()")
                }
            } catch (e: IllegalArgumentException) {
                _createdOrganizerResource.value = ResourceState.error(e.message)
            }
        }
    }
}