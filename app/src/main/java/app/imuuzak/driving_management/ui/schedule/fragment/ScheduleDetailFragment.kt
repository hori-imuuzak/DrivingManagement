package app.imuuzak.driving_management.ui.schedule.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import app.imuuzak.driving_management.DrivingManagementApp
import app.imuuzak.driving_management.R
import app.imuuzak.driving_management.databinding.FragmentScheduleDetailBinding
import app.imuuzak.driving_management.di.ViewModelFactory
import app.imuuzak.driving_management.ui.schedule.viewmodel.ScheduleDetailViewModel
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import javax.inject.Inject

class ScheduleDetailFragment @Inject constructor(): Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: ScheduleDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity?.application as DrivingManagementApp).getComponent().inject(this)

        val binding = DataBindingUtil.inflate<FragmentScheduleDetailBinding>(
            inflater,
            R.layout.fragment_schedule_detail,
            container,
            false
        )

        setupUI(binding)
        bind(binding)

        return binding.root
    }

    private fun setupUI(binding: FragmentScheduleDetailBinding) {
        val trackEvent = navArgs<ScheduleDetailFragmentArgs>().value.trackEvent

        this.viewModel = ViewModelProvider(this, viewModelFactory).get(ScheduleDetailViewModel::class.java)
        this.viewModel.setTrackEvent(trackEvent)

        (childFragmentManager.findFragmentById(R.id.mapFragment) as? SupportMapFragment)?.let {
            it.getMapAsync { map ->
                val place = LatLng(35.301318, 139.480219)
                map.moveCamera(CameraUpdateFactory.newLatLng(place))
            }
        }
    }

    private fun bind(binding: FragmentScheduleDetailBinding) {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = this.viewModel
    }
}