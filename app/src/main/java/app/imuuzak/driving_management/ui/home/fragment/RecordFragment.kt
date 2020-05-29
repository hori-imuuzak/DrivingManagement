package app.imuuzak.driving_management.ui.home.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import app.imuuzak.driving_management.R
import app.imuuzak.driving_management.databinding.FragmentRecordBinding
import app.imuuzak.driving_management.ui.home.viewmodel.RecordViewModel
import app.imuuzak.driving_management.ui.record.activity.CreateRecordActivity

class RecordFragment : Fragment() {

    private lateinit var recordViewModel: RecordViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        recordViewModel =
            ViewModelProvider(this).get(RecordViewModel::class.java)

        val binding = DataBindingUtil.inflate<FragmentRecordBinding>(inflater, R.layout.fragment_record, container, false)

        bind(binding)

        return binding.root
    }

    private fun bind(binding: FragmentRecordBinding) {
        binding.uiEvent = object: UIEvent {
            override fun onClickFloatingActionButton() {
                val intent =
                    Intent(activity, CreateRecordActivity::class.java)
                startActivity(intent)
            }
        }
    }

    interface UIEvent {
        fun onClickFloatingActionButton()
    }
}
