package app.imuuzak.driving_management.ui.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import app.imuuzak.driving_management.R
import app.imuuzak.driving_management.databinding.FragmentMaintenanceBinding
import app.imuuzak.driving_management.ui.home.adapter.MaintenanceListAdapter
import app.imuuzak.driving_management.ui.home.viewmodel.MaintenanceViewModel
import app.imuuzak.driving_management.ui.maintenance.dialog.CreateCarDialogFragment

class MaintenanceFragment : Fragment() {
    private lateinit var viewModel: MaintenanceViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentMaintenanceBinding>(
            inflater,
            R.layout.fragment_maintenance,
            container,
            false
        )

        viewModel = ViewModelProvider(this).get(MaintenanceViewModel::class.java)

        bind(binding)
        setupUI(binding)
        observe()

        return binding.root
    }

    private fun bind(binding: FragmentMaintenanceBinding) {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
    }

    private fun setupUI(binding: FragmentMaintenanceBinding) {
        binding.maintenanceList.adapter =
            MaintenanceListAdapter(resources.getStringArray(R.array.maintenance).toList())
    }

    private fun observe() {
        viewModel.onClickCreateCar.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                CreateCarDialogFragment().show(childFragmentManager, CreateCarDialogFragment.tagName)
            }
        })
    }
}
