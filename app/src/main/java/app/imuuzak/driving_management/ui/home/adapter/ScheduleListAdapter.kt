package app.imuuzak.driving_management.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import app.imuuzak.driving_management.R
import app.imuuzak.driving_management.databinding.ScheduleListRowBinding

class ScheduleListAdapter: RecyclerView.Adapter<ScheduleListAdapter.ViewHolder>() {
    override fun getItemCount(): Int {
        return 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ScheduleListRowBinding>(
            LayoutInflater.from(parent.context),
            R.layout.schedule_list_row,
            parent,
            false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // TODO
    }

    class ViewHolder(val binding: ScheduleListRowBinding): RecyclerView.ViewHolder(binding.root)
}