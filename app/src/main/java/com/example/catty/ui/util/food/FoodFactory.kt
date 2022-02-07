package com.example.catty.ui.util.food

import com.example.catty.models.FoodItem
import kotlinx.coroutines.Deferred

interface FoodFactory {
    fun createRandomFoodItemsList(from: Int,to: Int): Deferred<List<FoodItem>>
}