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
import app.imuuzak.driving_management.viewmodel.home.ScheduleViewModel

class ScheduleFragment : Fragment() {

    private lateinit var scheduleViewModel: ScheduleViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        scheduleViewModel =
            ViewModelProviders.of(this).get(ScheduleViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_schedule, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        scheduleViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}
