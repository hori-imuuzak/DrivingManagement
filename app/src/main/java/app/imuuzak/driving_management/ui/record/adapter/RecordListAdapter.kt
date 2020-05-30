package app.imuuzak.driving_management.ui.record.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import app.imuuzak.driving_management.R
import app.imuuzak.driving_management.databinding.RecordListRowBinding

class RecordListAdapter : RecyclerView.Adapter<RecordListAdapter.RecordListRowViewHolder>() {

    override fun getItemCount() = 10

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

    }

    class RecordListRowViewHolder(val binding: RecordListRowBinding): RecyclerView.ViewHolder(binding.root)
}