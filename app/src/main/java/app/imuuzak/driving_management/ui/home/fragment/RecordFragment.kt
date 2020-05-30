package app.imuuzak.driving_management.ui.home.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import app.imuuzak.driving_management.DrivingManagementApp
import app.imuuzak.driving_management.R
import app.imuuzak.driving_management.databinding.FragmentRecordBinding
import app.imuuzak.driving_management.di.ViewModelFactory
import app.imuuzak.driving_management.ui.home.adapter.RecordCircuitListAdapter
import app.imuuzak.driving_management.ui.home.viewmodel.RecordViewModel
import app.imuuzak.driving_management.ui.record.activity.CreateRecordActivity
import javax.inject.Inject

class RecordFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var recordViewModel: RecordViewModel
    private lateinit var circuitListAdapter: RecordCircuitListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity?.application as DrivingManagementApp).getComponent().inject(this)

        recordViewModel =
            ViewModelProvider(this, viewModelFactory).get(RecordViewModel::class.java)

        val binding = DataBindingUtil.inflate<FragmentRecordBinding>(
            inflater,
            R.layout.fragment_record,
            container,
            false
        )

        setupUI(binding)
        bind(binding)
        observe()

        recordViewModel.loadHasRecordCircuitList()

        return binding.root
    }

    private fun setupUI(binding: FragmentRecordBinding) {
        circuitListAdapter = RecordCircuitListAdapter(viewLifecycleOwner, recordViewModel)
        binding.circuitList.adapter = circuitListAdapter
    }

    private fun bind(binding: FragmentRecordBinding) {
        binding.uiEvent = object : UIEvent {
            override fun onClickFloatingActionButton() {
                val intent =
                    Intent(activity, CreateRecordActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun observe() {
        recordViewModel.circuitList.observe(viewLifecycleOwner, Observer {
            circuitListAdapter.notifyDataSetChanged()
        })

        recordViewModel.selectCircuit.observe(viewLifecycleOwner, Observer {
            it?.let {
                findNavController().navigate(RecordFragmentDirections.actionNavigationRecordToRecordListFragment(it))
            }
        })
    }

    override fun onPause() {
        super.onPause()

        recordViewModel.setSelectCircuit(null)
    }

    interface UIEvent {
        fun onClickFloatingActionButton()
    }
}
