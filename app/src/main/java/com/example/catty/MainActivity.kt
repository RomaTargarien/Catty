package com.example.catty

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.BounceInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.catty.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.lang.IndexOutOfBoundsException
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var foodAdapter: FoodAdapter
    private lateinit var recyclerViewFood: RecyclerView

    private val delayFlow: MutableStateFlow<Long> = MutableStateFlow(25L)
    private val scrollDxFlow: MutableStateFlow<Int> = MutableStateFlow(5)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        foodAdapter = FoodAdapter()
        recyclerViewFood = binding.rv
        val images = mutableListOf(
            R.drawable.onion,
            R.drawable.pork,
            R.drawable.table,
            R.drawable.potato
        )
        val foodItems = mutableListOf<FoodItem>()
        for (i in 0..10) {
            val foodItem = FoodItem()
            images.random().apply {
                val uid: String = UUID.randomUUID().toString()
                foodItem.image = this
                foodItem.eatable = this != R.drawable.table
                foodItem.number = i
                foodItem.uid = uid
            }
            foodItems.add(foodItem)
        }
        binding.bnFaster.setOnClickListener {
            lifecycleScope.launch {
                delayFlow.emit(delayFlow.value+10)
                scrollDxFlow.emit(scrollDxFlow.value + 10)
            }
        }
        setUpFoodItems(foodItems)
    }

    private fun setUpFoodItems(foodItems: List<FoodItem>) {
        with(recyclerViewFood) {
            layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
            adapter = foodAdapter
        }
        foodAdapter.submitList(foodItems)
        lifecycleScope.launch { autoScrollFoodList() }
    }

    private tailrec suspend fun autoScrollFoodList() {
        if (recyclerViewFood.canScrollHorizontally(DIRECTION_RIGHT)) {
            recyclerViewFood.smoothScrollBy(scrollDxFlow.value,0)
        } else {
            val firstPosition = (recyclerViewFood.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
            if (firstPosition != RecyclerView.NO_POSITION) {
                val currentList = foodAdapter.currentList
                val secondPart = currentList.subList(0,firstPosition)
                val firstPart = currentList.subList(firstPosition,currentList.size)
                foodAdapter.submitList(firstPart + secondPart)
            }
        }
        delay(delayFlow.value)
        autoScrollFoodList()
    }

    companion object {
        private const val DIRECTION_RIGHT = 1
    }
}