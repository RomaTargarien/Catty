package com.example.catty.ui.screens.game

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.catty.databinding.ItemFoodBinding
import com.example.catty.models.FoodItem


class FoodAdapter : ListAdapter<FoodItem,RecyclerView.ViewHolder>(FoodItemDiffCallback()) {

    inner class FoodItemViewHoler(val binding: ItemFoodBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: FoodItem) {
            binding.ivFood.setImageResource(item.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodItemViewHoler {
        return FoodItemViewHoler(
            ItemFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemViewHolder = holder as FoodItemViewHoler
        itemViewHolder.bind(getItem(position))

    }

    class FoodItemDiffCallback : DiffUtil.ItemCallback<FoodItem>() {

        override fun areItemsTheSame(oldItem: FoodItem, newItem: FoodItem): Boolean =
            oldItem.uid== newItem.uid

        override fun areContentsTheSame(oldItem: FoodItem, newItem: FoodItem): Boolean =
            oldItem == newItem
    }
}

