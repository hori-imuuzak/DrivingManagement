package app.imuuzak.driving_management.ui.record.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import app.imuuzak.driving_management.R
import app.imuuzak.driving_management.databinding.AddRecordTimeListRowBinding
import app.imuuzak.driving_management.ui.record.viewmodel.CreateRecordViewModel

class RecordTimeListAdapter(
    val lifecycleOwner: LifecycleOwner,
    val viewModel: CreateRecordViewModel
) : RecyclerView.Adapter<RecordTimeListAdapter.RecordTimeListItemViewHolder>() {
    override fun getItemCount(): Int = viewModel.recordTimeList.value?.size ?: 0

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecordTimeListItemViewHolder {
        val binding = DataBindingUtil.inflate<AddRecordTimeListRowBinding>(
            LayoutInflater.from(parent.context),
            R.layout.add_record_time_list_row,
            parent,
            false
        )

        binding.lifecycleOwner = lifecycleOwner

        return RecordTimeListItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecordTimeListItemViewHolder, position: Int) {
        holder.binding.viewModel = viewModel
        holder.binding.position = position
        holder.binding.executePendingBindings()
    }

    class RecordTimeListItemViewHolder(val binding: AddRecordTimeListRowBinding) :
        RecyclerView.ViewHolder(binding.root)
}