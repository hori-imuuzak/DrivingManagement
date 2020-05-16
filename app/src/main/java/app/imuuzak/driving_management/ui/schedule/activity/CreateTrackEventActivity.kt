package app.imuuzak.driving_management.ui.schedule.activity

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import app.imuuzak.driving_management.R
import app.imuuzak.driving_management.databinding.ActivityCreateTrackEventBinding
import app.imuuzak.driving_management.ui.circuit.activity.CreateCircuitActivity
import app.imuuzak.driving_management.ui.schedule.viewmodel.CreateTrackEventViewModel
import java.util.*

class CreateTrackEventActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityCreateTrackEventBinding>(
            this,
            R.layout.activity_create_track_event
        )

        bind(binding)
    }

    private fun bind(binding: ActivityCreateTrackEventBinding) {
        binding.lifecycleOwner = this
        val viewModel = CreateTrackEventViewModel()

        binding.viewModel = viewModel

        binding.uiEvent = object : UIEvent {
            override fun onClickCreateCircuit() {
                val intent = Intent(this@CreateTrackEventActivity, CreateCircuitActivity::class.java)
                startActivity(intent)
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
        }
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

    companion object {
        interface UIEvent {
            fun onClickCreateCircuit()
            fun onClickMeetingDate()
            fun onClickMeetingTime()
            fun onClickDismissalTime()
            fun onClickPaymentDeadline()
        }
    }
}
