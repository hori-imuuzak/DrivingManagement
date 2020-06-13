package app.imuuzak.driving_management.ui.record.fragment

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import app.imuuzak.driving_management.R
import app.imuuzak.driving_management.databinding.FragmentRecordDetailBinding
import app.imuuzak.driving_management.databinding.RecordDetailLaptimeRowBinding
import app.imuuzak.driving_management.domain.model.Record
import app.imuuzak.driving_management.ui.record.viewmodel.RecordDetailViewModel

class RecordDetailFragment : Fragment() {

    lateinit var viewModel: RecordDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)

        val binding = DataBindingUtil.inflate<FragmentRecordDetailBinding>(
            inflater,
            R.layout.fragment_record_detail,
            container,
            false
        )

        val record = navArgs<RecordDetailFragmentArgs>().value.record
        viewModel = ViewModelProvider(this).get(RecordDetailViewModel::class.java)
        viewModel.setRecord(record)

        setupUI(binding, record)
        bind(binding)

        return binding.root
    }

    private fun setupUI(binding: FragmentRecordDetailBinding, record: Record) {
        record.recordList.map {
            val recordDetailLapTimeListRowBinding =
                DataBindingUtil.inflate<RecordDetailLaptimeRowBinding>(
                    LayoutInflater.from(context),
                    R.layout.record_detail_laptime_row,
                    binding.lapTimeContainer,
                    false
                )

            recordDetailLapTimeListRowBinding.text = it.format()

            binding.lapTimeContainer.addView(recordDetailLapTimeListRowBinding.root)
        }
    }

    private fun bind(binding: FragmentRecordDetailBinding) {
        binding.lifecycleOwner = viewLifecycleOwner

        binding.recordDetailViewModel = viewModel
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.record_detail_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuDelete -> {
                return true
            }
        }

        return NavigationUI.onNavDestinationSelected(item, findNavController()) || super.onOptionsItemSelected(item)
    }
}