package app.imuuzak.driving_management.ui.record.activity

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import app.imuuzak.driving_management.DrivingManagementApp
import app.imuuzak.driving_management.R
import app.imuuzak.driving_management.databinding.ActivityCreateRecordBinding
import app.imuuzak.driving_management.di.ViewModelFactory
import app.imuuzak.driving_management.domain.model.value.RecordTime
import app.imuuzak.driving_management.domain.repository.ResourceState
import app.imuuzak.driving_management.ui.record.adapter.RecordTimeListAdapter
import app.imuuzak.driving_management.ui.record.dialog.LapTimePickerDialogFragment
import app.imuuzak.driving_management.ui.record.viewmodel.CreateRecordViewModel
import com.google.android.material.snackbar.Snackbar
import java.util.*
import javax.inject.Inject

class CreateRecordActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var viewModel: CreateRecordViewModel
    lateinit var recordTimeListAdapter: RecordTimeListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as DrivingManagementApp).getComponent().inject(this)

        val binding = DataBindingUtil.setContentView<ActivityCreateRecordBinding>(
            this,
            R.layout.activity_create_record
        )

        viewModel = ViewModelProvider(this, viewModelFactory).get(CreateRecordViewModel::class.java)

        setupUI(binding)
        bind(binding)
        observe()

        viewModel.loadCircuitList()
    }

    private fun setupUI(binding: ActivityCreateRecordBinding) {
        recordTimeListAdapter = RecordTimeListAdapter(this, viewModel)
        binding.lapTimeList.adapter = recordTimeListAdapter
    }

    private fun bind(binding: ActivityCreateRecordBinding) {
        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        binding.uiEvent = object : UIEvent {
            override fun onClickPickDate() {
                showDatePicker(DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                    val cal = Calendar.getInstance().apply {
                        set(Calendar.YEAR, year)
                        set(Calendar.MONTH, month)
                        set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    }
                    viewModel.setDate(cal.time)
                })
            }

            override fun onClickAddLapTime() {
                LapTimePickerDialogFragment(
                    listener = object : LapTimePickerDialogFragment.OnRecordTimePickListener {
                        override fun onPicked(recordTime: RecordTime) {
                            viewModel.addRecordTime(recordTime)
                        }
                    }
                ).show(supportFragmentManager, "lapTimePickerDialog")
            }

            override fun onClickCreate() {
                viewModel.createRecord().observe(this@CreateRecordActivity, Observer {
                    when (it.status) {
                        ResourceState.Status.LOADING -> {

                        }
                        ResourceState.Status.ERROR -> {
                            it.message?.let { message -> showSnackbar(message) }
                        }
                        ResourceState.Status.SUCCESS -> {
                            finish()
                        }
                    }
                })
            }
        }
    }

    private fun observe() {
        viewModel.recordTimeList.observe(this, Observer {
            recordTimeListAdapter.notifyDataSetChanged()
        })
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

    interface UIEvent {
        fun onClickPickDate()
        fun onClickAddLapTime()
        fun onClickCreate()
    }
}