package app.imuuzak.driving_management.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import app.imuuzak.driving_management.R
import app.imuuzak.driving_management.databinding.HasRecordCircuitListRowBinding
import app.imuuzak.driving_management.ui.home.viewmodel.RecordViewModel

class RecordCircuitListAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val recordViewModel: RecordViewModel
): RecyclerView.Adapter<RecordCircuitListAdapter.RecordCircuitListRowViewHolder>() {

    override fun getItemCount() = recordViewModel.circuitList.value?.size ?: 0

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecordCircuitListRowViewHolder {
        val binding = DataBindingUtil.inflate<HasRecordCircuitListRowBinding>(
            LayoutInflater.from(parent.context),
            R.layout.has_record_circuit_list_row,
            parent,
            false
        )

        binding.lifecycleOwner = lifecycleOwner

        return RecordCircuitListRowViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecordCircuitListRowViewHolder, position: Int) {
        holder.binding.position = position
        holder.binding.viewModel = recordViewModel
        holder.binding.executePendingBindings()
    }

    class RecordCircuitListRowViewHolder(val binding: HasRecordCircuitListRowBinding) : RecyclerView.ViewHolder(binding.root)
}