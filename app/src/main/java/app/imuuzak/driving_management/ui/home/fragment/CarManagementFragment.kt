package app.imuuzak.driving_management.ui.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import app.imuuzak.driving_management.R
import app.imuuzak.driving_management.ui.home.viewmodel.CarManagementViewModel

class CarManagementFragment : Fragment() {

    private lateinit var carManagementViewModel: CarManagementViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        carManagementViewModel =
            ViewModelProviders.of(this).get(CarManagementViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_car_management, container, false)
        val textView: TextView = root.findViewById(R.id.text_notifications)
        carManagementViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}
