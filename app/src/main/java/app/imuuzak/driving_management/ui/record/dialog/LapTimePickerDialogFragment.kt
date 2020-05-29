package app.imuuzak.driving_management.ui.record.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import app.imuuzak.driving_management.R
import app.imuuzak.driving_management.databinding.FragmentLaptimePickerBinding
import app.imuuzak.driving_management.domain.model.value.RecordTime
import app.imuuzak.driving_management.ui.record.viewmodel.LapTimePickerDialogViewModel

class LapTimePickerDialogFragment(val listener: OnRecordTimePickListener) : DialogFragment() {
    private lateinit var viewModel: LapTimePickerDialogViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentLaptimePickerBinding>(inflater, R.layout.fragment_laptime_picker, container, false)

        viewModel = ViewModelProvider(this).get(LapTimePickerDialogViewModel::class.java)

        bind(binding)
        observe()

        return binding.root
    }

    private fun bind(binding: FragmentLaptimePickerBinding) {
        binding.lifecycleOwner = viewLifecycleOwner

        binding.viewModel = viewModel
    }

    private fun observe() {
        viewModel.recordTime.observe(viewLifecycleOwner, Observer {
            listener.onPicked(it)
            dismiss()
        })
    }

    interface OnRecordTimePickListener {
        fun onPicked(recordTime: RecordTime)
    }
}