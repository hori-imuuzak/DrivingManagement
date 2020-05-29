package app.imuuzak.driving_management.ui.schedule.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import app.imuuzak.driving_management.DrivingManagementApp
import app.imuuzak.driving_management.R
import app.imuuzak.driving_management.databinding.FragmentScheduleDetailBinding
import app.imuuzak.driving_management.di.ViewModelFactory
import app.imuuzak.driving_management.ui.record.activity.CreateRecordActivity
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
        observe(binding)

        return binding.root
    }

    private fun setupUI(binding: FragmentScheduleDetailBinding) {
        val trackEvent = navArgs<ScheduleDetailFragmentArgs>().value.trackEvent

        this.viewModel = ViewModelProvider(this, viewModelFactory).get(ScheduleDetailViewModel::class.java)
        this.viewModel.setTrackEvent(trackEvent)

        (childFragmentManager.findFragmentById(R.id.mapFragment) as? SupportMapFragment)?.let {
            it.getMapAsync { map ->
                val place = LatLng(35.301318, 139.480219)
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(place, 13.0f))
            }
        }

        binding.uiEvent = object: UIEvent {
            override fun onClickFloatingActionButton() {
                val intent =
                    Intent(activity, CreateRecordActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun bind(binding: FragmentScheduleDetailBinding) {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = this.viewModel
    }

    private fun observe(binding: FragmentScheduleDetailBinding) {
        viewModel.belongingTextList.observe(viewLifecycleOwner, Observer {
            it.forEach { belongingName ->
                belongingName?.let { name ->
                    val textView = TextView(context).apply {
                        text = name
                        val paddingTop = resources.getDimensionPixelSize(R.dimen.medium)
                        setPadding(0, paddingTop, 0, 0)
                    }
                    binding.belongingsContainerView.addView(textView)
                }
            }
        })
    }

    interface UIEvent {
        fun onClickFloatingActionButton()
    }
}