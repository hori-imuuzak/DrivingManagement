package app.imuuzak.driving_management.ui.record.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import app.imuuzak.driving_management.R
import app.imuuzak.driving_management.databinding.RecordListRowBinding
import app.imuuzak.driving_management.ui.record.viewmodel.RecordListViewModel

class RecordListAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val recordListViewModel: RecordListViewModel
) : RecyclerView.Adapter<RecordListAdapter.RecordListRowViewHolder>() {

    override fun getItemCount() = recordListViewModel.recordList.value?.size ?: 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordListRowViewHolder {
        val binding = DataBindingUtil.inflate<RecordListRowBinding>(
            LayoutInflater.from(parent.context),
            R.layout.record_list_row,
            parent,
            false
        )

        return RecordListRowViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecordListRowViewHolder, position: Int) {
        holder.binding.lifecycleOwner = lifecycleOwner
        holder.binding.recordListViewModel = recordListViewModel
        holder.binding.position = position
        holder.binding.executePendingBindings()
    }

    class RecordListRowViewHolder(val binding: RecordListRowBinding) :
        RecyclerView.ViewHolder(binding.root)
}