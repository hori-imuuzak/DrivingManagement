package app.imuuzak.driving_management.ui.record.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import app.imuuzak.driving_management.DrivingManagementApp
import app.imuuzak.driving_management.R
import app.imuuzak.driving_management.databinding.FragmentRecordListBinding
import app.imuuzak.driving_management.di.ViewModelFactory
import app.imuuzak.driving_management.ui.record.adapter.RecordListAdapter
import app.imuuzak.driving_management.ui.record.viewmodel.RecordListViewModel
import javax.inject.Inject

class RecordListFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var recordListViewModel: RecordListViewModel
    private lateinit var recordListAdapter: RecordListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity?.application as DrivingManagementApp).getComponent().inject(this)

        val binding = DataBindingUtil.inflate<FragmentRecordListBinding>(
            inflater,
            R.layout.fragment_record_list,
            container,
            false
        )

        val circuit = navArgs<RecordListFragmentArgs>().value.circuit

        recordListViewModel = ViewModelProvider(this, viewModelFactory).get(RecordListViewModel::class.java)

        setupUI(binding)
        observe()

        recordListViewModel.loadRecord(circuit)

        return binding.root
    }

    private fun setupUI(binding: FragmentRecordListBinding) {
        recordListAdapter = RecordListAdapter()
        binding.recordList.adapter = recordListAdapter
    }

    private fun observe() {
        recordListViewModel.recordList.observe(viewLifecycleOwner, Observer {
            recordListAdapter.notifyDataSetChanged()
        })
    }
}