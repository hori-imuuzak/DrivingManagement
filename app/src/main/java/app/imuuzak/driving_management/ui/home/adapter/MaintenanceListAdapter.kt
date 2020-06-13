package app.imuuzak.driving_management.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import app.imuuzak.driving_management.R
import app.imuuzak.driving_management.databinding.MaintenanceItemBinding

class MaintenanceListAdapter(private val maintenanceItemList: List<String>) :
    RecyclerView.Adapter<MaintenanceListAdapter.MaintenanceItemViewHolder>() {

    override fun getItemCount() = maintenanceItemList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MaintenanceItemViewHolder {
        val binding = DataBindingUtil.inflate<MaintenanceItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.maintenance_item,
            parent,
            false
        )

        return MaintenanceItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MaintenanceItemViewHolder, position: Int) {
        holder.binding.text = maintenanceItemList[position]
        holder.binding.executePendingBindings()
    }

    class MaintenanceItemViewHolder(val binding: MaintenanceItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}