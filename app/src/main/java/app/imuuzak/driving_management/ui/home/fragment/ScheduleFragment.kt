package app.imuuzak.driving_management.ui.home.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import app.imuuzak.driving_management.DrivingManagementApp
import app.imuuzak.driving_management.R
import app.imuuzak.driving_management.databinding.FragmentScheduleBinding
import app.imuuzak.driving_management.di.ViewModelFactory
import app.imuuzak.driving_management.ui.home.adapter.ScheduleListAdapter
import app.imuuzak.driving_management.ui.home.viewmodel.ScheduleViewModel
import app.imuuzak.driving_management.ui.schedule.activity.CreateTrackEventActivity
import javax.inject.Inject

class ScheduleFragment @Inject constructor() : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: ScheduleViewModel
    private lateinit var scheduleListAdapter: ScheduleListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity?.application as DrivingManagementApp).getComponent().inject(this)

        this.viewModel =
            ViewModelProvider(this, viewModelFactory).get(ScheduleViewModel::class.java)
        val binding = DataBindingUtil.inflate<FragmentScheduleBinding>(
            layoutInflater,
            R.layout.fragment_schedule,
            container,
            false
        )

        setupUI(binding)
        bind(binding)
        observe()

        viewModel.getTrackEventList()

        return binding.root
    }

    private fun setupUI(binding: FragmentScheduleBinding) {
        scheduleListAdapter = ScheduleListAdapter(viewLifecycleOwner, viewModel)
        binding.scheduleRecyclerView.adapter = scheduleListAdapter
    }

    private fun bind(binding: FragmentScheduleBinding) {
        binding.uiEvent = object : UIEvent {
            override fun onClickFloatingActionButton() {
                toCreateTrackEvent()
            }
        }
    }

    private fun observe() {
        viewModel.trackEventList.observe(viewLifecycleOwner, Observer {
            scheduleListAdapter.notifyItemRangeChanged(0, it.size)
        })
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
