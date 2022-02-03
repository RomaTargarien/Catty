package com.example.catty.ui.util

import com.example.catty.R
import com.example.catty.models.FoodItem
import com.example.catty.ui.screens.game.GameFragment
import java.util.*

class FoodUtil {

    var currentListFoodItems = mutableListOf<FoodItem>()
    var randomListStartNumber = 0

    fun generateRandomList() {
        val foodItems = mutableListOf<FoodItem>()
        for (i in randomListStartNumber until LIST_SIZE + randomListStartNumber) {
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
        randomListStartNumber += LIST_SIZE
        currentListFoodItems = mutableListOf()
        currentListFoodItems.addAll(foodItems)
    }


    companion object {
        private const val LIST_SIZE = 10
        val images = mutableListOf(
            R.drawable.brocolli,
            R.drawable.brocolli,
            R.drawable.brocolli,
            R.drawable.potato
        )
    }
}