package app.imuuzak.driving_management.ui.record.fragment

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import app.imuuzak.driving_management.R
import app.imuuzak.driving_management.databinding.FragmentRecordDetailBinding

class RecordDetailFragment : Fragment() {

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

        setupUI(binding)

        return binding.root
    }

    private fun setupUI(binding: FragmentRecordDetailBinding) {
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