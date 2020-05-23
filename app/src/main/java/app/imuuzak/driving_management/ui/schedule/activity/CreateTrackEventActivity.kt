package app.imuuzak.driving_management.ui.schedule.activity

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import app.imuuzak.driving_management.DrivingManagementApp
import app.imuuzak.driving_management.R
import app.imuuzak.driving_management.databinding.ActivityCreateTrackEventBinding
import app.imuuzak.driving_management.di.ViewModelFactory
import app.imuuzak.driving_management.domain.model.Belonging
import app.imuuzak.driving_management.domain.repository.ResourceState
import app.imuuzak.driving_management.ui.circuit.activity.CreateCircuitActivity
import app.imuuzak.driving_management.ui.organizer.activity.CreateOrganizerActivity
import app.imuuzak.driving_management.ui.schedule.adapter.BelongingsListAdapter
import app.imuuzak.driving_management.ui.schedule.viewmodel.BelongingsListItemViewModel
import app.imuuzak.driving_management.ui.schedule.viewmodel.CreateTrackEventViewModel
import com.google.android.material.snackbar.Snackbar
import java.util.*
import javax.inject.Inject

class CreateTrackEventActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var viewModel: CreateTrackEventViewModel

    lateinit var belongingsListAdapter: BelongingsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as DrivingManagementApp).getComponent().inject(this)

        val binding = DataBindingUtil.setContentView<ActivityCreateTrackEventBinding>(
            this,
            R.layout.activity_create_track_event
        )

        viewModel =
            ViewModelProvider(this, viewModelFactory).get(CreateTrackEventViewModel::class.java)

        setupUI(binding)
        bind(binding)
    }

    override fun onResume() {
        super.onResume()

        observe()
    }

    private fun setupUI(binding: ActivityCreateTrackEventBinding) {
        belongingsListAdapter = BelongingsListAdapter()
        binding.belongingsRecyclerView.adapter = belongingsListAdapter
    }

    private fun bind(binding: ActivityCreateTrackEventBinding) {
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.uiEvent = object : UIEvent {
            override fun onClickCreateCircuit() {
                val intent =
                    Intent(this@CreateTrackEventActivity, CreateCircuitActivity::class.java)
                startActivity(intent)
            }

            override fun onClickCreateOrganizer() {
                val intent =
                    Intent(this@CreateTrackEventActivity, CreateOrganizerActivity::class.java)
                startActivity(intent)
            }

            override fun onClickCreateBelonging() {
                val belongingsListViewModel =
                    ViewModelProvider(this@CreateTrackEventActivity).get(BelongingsListItemViewModel::class.java)
                belongingsListViewModel.setBelonging(Belonging("sample", 1))
                belongingsListViewModel.uiEvent = object: BelongingsListItemViewModel.UIEvent {
                    override fun onClickRemove() {
                        val removedAt = belongingsListAdapter.removeBelonging(belongingsListViewModel)
                        belongingsListAdapter.notifyItemRemoved(removedAt)
                        viewModel.removeBelonging()
                    }
                }

                belongingsListAdapter.addBelonging(belongingsListViewModel)
                belongingsListAdapter.notifyItemInserted(belongingsListAdapter.itemCount - 1)
                viewModel.addBelonging()
            }

            override fun onClickMeetingDate() {
                showDatePicker(DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                    viewModel.setMeetingDate(year, month, dayOfMonth)
                })
            }

            override fun onClickMeetingTime() {
                showTimePicker(TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                    viewModel.setMeetingTime(hour = hourOfDay, minute = minute)
                })
            }

            override fun onClickDismissalTime() {
                showTimePicker(TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                    viewModel.setDismissalTime(hour = hourOfDay, minute = minute)
                })
            }

            override fun onClickPaymentDeadline() {
                showDatePicker(DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                    viewModel.setPaymentDeadline(year, month, dayOfMonth)
                })
            }

            override fun onClickCreate() {
                viewModel.createTrackEvent(belongingsListAdapter.belongingsListItemViewModels)
            }
        }
    }

    private fun observe() {
        viewModel.loadCircuitList().observe(this, Observer {
            viewModel.setCircuitList(it)
        })

        viewModel.loadOrganizerList().observe(this, Observer {
            viewModel.setOrganizerList(it)
        })

        viewModel.createdTrackEventResource.observe(this, Observer {
            when (it.status) {
                ResourceState.Status.LOADING -> {
                    // ローディング出す
                }
                ResourceState.Status.SUCCESS -> {
                    finish()
                }
                ResourceState.Status.ERROR -> {
                    print(it.message)
                    showSnackbar(getString(R.string.error_create))
                }
            }
        })
    }

    private fun showTimePicker(listener: TimePickerDialog.OnTimeSetListener) {
        TimePickerDialog(this, listener, 9, 0, true).show()
    }

    private fun showDatePicker(listener: DatePickerDialog.OnDateSetListener) {
        val cal = Calendar.getInstance()
        DatePickerDialog(
            this,
            listener,
            cal.get(Calendar.YEAR),
            cal.get(Calendar.MONTH),
            cal.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun showSnackbar(str: String) {
        Snackbar.make(findViewById<View>(android.R.id.content), str, Snackbar.LENGTH_SHORT).show()
    }

    companion object {
        interface UIEvent {
            fun onClickCreateCircuit()
            fun onClickCreateOrganizer()
            fun onClickCreateBelonging()
            fun onClickMeetingDate()
            fun onClickMeetingTime()
            fun onClickDismissalTime()
            fun onClickPaymentDeadline()
            fun onClickCreate()
        }
    }
}
