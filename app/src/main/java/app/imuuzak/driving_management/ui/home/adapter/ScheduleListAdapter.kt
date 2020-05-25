package app.imuuzak.driving_management.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import app.imuuzak.driving_management.R
import app.imuuzak.driving_management.databinding.ScheduleListRowBinding
import app.imuuzak.driving_management.ui.home.viewmodel.ScheduleViewModel

class ScheduleListAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val scheduleViewModel: ScheduleViewModel
) : RecyclerView.Adapter<ScheduleListAdapter.ViewHolder>() {

    override fun getItemCount() = scheduleViewModel.trackEventList.value?.size ?: 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ScheduleListRowBinding>(
            LayoutInflater.from(parent.context),
            R.layout.schedule_list_row,
            parent,
            false
        )

        binding.lifecycleOwner = lifecycleOwner
        binding.viewModel = scheduleViewModel

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.position = position
        scheduleViewModel.getWeather(position)
        holder.binding.executePendingBindings()
    }

    class ViewHolder(val binding: ScheduleListRowBinding) : RecyclerView.ViewHolder(binding.root)
}