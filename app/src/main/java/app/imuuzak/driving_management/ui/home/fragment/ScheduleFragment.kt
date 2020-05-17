package app.imuuzak.driving_management.ui.home.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import app.imuuzak.driving_management.R
import app.imuuzak.driving_management.databinding.FragmentScheduleBinding
import app.imuuzak.driving_management.ui.home.viewmodel.ScheduleViewModel
import app.imuuzak.driving_management.ui.schedule.activity.CreateTrackEventActivity

class ScheduleFragment : Fragment() {

    private lateinit var scheduleViewModel: ScheduleViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        scheduleViewModel =
            ViewModelProviders.of(this).get(ScheduleViewModel::class.java)
        val binding = DataBindingUtil.inflate<FragmentScheduleBinding>(layoutInflater, R.layout.fragment_schedule, container, false)

        bind(binding)

        return binding.root
    }

    private fun bind(binding: FragmentScheduleBinding) {
        binding.uiEvent = object: UIEvent {
            override fun onClickFloatingActionButton() {
                toCreateTrackEvent()
            }
        }
    }

    private fun toCreateTrackEvent() {
        val intent = Intent(activity, CreateTrackEventActivity::class.java)
        startActivity(intent)
    }

    companion object {
        interface UIEvent {
            fun onClickFloatingActionButton()
        }
    }
}
