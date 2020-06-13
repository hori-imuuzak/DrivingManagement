package app.imuuzak.driving_management.ui.maintenance.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import app.imuuzak.driving_management.R
import app.imuuzak.driving_management.databinding.FragmentCreateCarBinding
import app.imuuzak.driving_management.ui.maintenance.viewmodel.CreateCarViewModel

class CreateCarDialogFragment : DialogFragment() {
    private lateinit var viewModel: CreateCarViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentCreateCarBinding>(inflater, R.layout.fragment_create_car, container, false)

        viewModel = ViewModelProvider(this).get(CreateCarViewModel::class.java)

        bind(binding)
        observe()

        return binding.root
    }

    private fun bind(binding: FragmentCreateCarBinding) {
        binding.lifecycleOwner = viewLifecycleOwner

        binding.viewModel = viewModel
    }

    private fun observe() {

    }

    companion object {
        const val tagName = "create_car_dialog_fragment"
    }
}