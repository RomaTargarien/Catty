package com.example.catty

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.catty.databinding.ItemFoodBinding
import java.lang.IndexOutOfBoundsException


class FoodAdapter : ListAdapter<FoodItem,RecyclerView.ViewHolder>(FoodItemDiffCallback()) {


    inner class FoodItemViewHoler(val binding: ItemFoodBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: FoodItem) {
            binding.ivFood.setImageResource(item.image)
            binding.tvNumber.text = item.number.toString()
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

