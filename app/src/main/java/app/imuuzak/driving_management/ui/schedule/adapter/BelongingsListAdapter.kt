package app.imuuzak.driving_management.ui.schedule.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import app.imuuzak.driving_management.R
import app.imuuzak.driving_management.databinding.AddBelongListRowBinding
import app.imuuzak.driving_management.ui.schedule.viewmodel.BelongingsListItemViewModel

class BelongingsListAdapter(belongingsListItemViewModels: List<BelongingsListItemViewModel> = listOf()) :
    RecyclerView.Adapter<BelongingsListAdapter.Companion.ViewHolder>() {

    val belongingsListItemViewModels = belongingsListItemViewModels.toMutableList()

    fun addBelonging(viewModel: BelongingsListItemViewModel) {
        belongingsListItemViewModels.add(viewModel)
    }
    fun removeBelonging(viewModel: BelongingsListItemViewModel): Int {
        val removedAt = belongingsListItemViewModels.indexOf(viewModel)
        belongingsListItemViewModels.remove(viewModel)
        return removedAt
    }

    override fun getItemCount(): Int {
        return belongingsListItemViewModels.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<AddBelongListRowBinding>(
            LayoutInflater.from(parent.context),
            R.layout.add_belong_list_row,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.viewModel = belongingsListItemViewModels[position]
    }

    companion object {
        class ViewHolder(val binding: AddBelongListRowBinding) :
            RecyclerView.ViewHolder(binding.root)
    }
}