package app.imuuzak.driving_management.ui.record.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import app.imuuzak.driving_management.R
import app.imuuzak.driving_management.databinding.FragmentRecordListBinding

class RecordListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentRecordListBinding>(
            inflater,
            R.layout.fragment_record_list,
            container,
            false
        )

        return binding.root
    }
}