package app.imuuzak.driving_management.ui.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import app.imuuzak.driving_management.R
import app.imuuzak.driving_management.databinding.FragmentMaintenanceBinding
import app.imuuzak.driving_management.ui.home.adapter.MaintenanceListAdapter

class MaintenanceFragment : Fragment() {

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

        setupUI(binding)

        return binding.root
    }

    private fun setupUI(binding: FragmentMaintenanceBinding) {
        binding.maintenanceList.adapter =
            MaintenanceListAdapter(resources.getStringArray(R.array.maintenance).toList())
    }
}
