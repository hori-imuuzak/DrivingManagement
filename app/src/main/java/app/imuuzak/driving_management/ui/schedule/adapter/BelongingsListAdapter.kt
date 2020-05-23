package app.imuuzak.driving_management.ui.schedule.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import app.imuuzak.driving_management.R
import app.imuuzak.driving_management.databinding.AddBelongListRowBinding
import app.imuuzak.driving_management.ui.schedule.viewmodel.CreateTrackEventViewModel

class BelongingsListAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val createTrackEventViewModel: CreateTrackEventViewModel
) :
    RecyclerView.Adapter<BelongingsListAdapter.Companion.ViewHolder>() {

    override fun getItemCount(): Int {
        return createTrackEventViewModel.belongingsCount.value ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<AddBelongListRowBinding>(
            LayoutInflater.from(parent.context),
            R.layout.add_belong_list_row,
            parent,
            false
        )

        binding.lifecycleOwner = lifecycleOwner
        binding.viewModel = createTrackEventViewModel

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.position = position
        holder.binding.executePendingBindings()
    }

    companion object {
        class ViewHolder(val binding: AddBelongListRowBinding) :
            RecyclerView.ViewHolder(binding.root)
    }
}