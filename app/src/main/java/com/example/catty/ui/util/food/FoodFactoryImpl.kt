package com.example.catty.ui.util.food

import com.example.catty.R
import com.example.catty.models.FoodItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import java.util.*

class FoodFactoryImpl : FoodFactory {

    override fun createRandomFoodItemsList(from: Int, to: Int) =
        CoroutineScope(Dispatchers.Default).async {
            val foodItems = mutableListOf<FoodItem>()
            for (i in from until to) {
                val foodItem = FOOD_ITEMS_LIST.random().copy().apply {
                    uid = UUID.randomUUID().toString()
                    number = i
                }
                foodItems.add(foodItem)
            }
            foodItems
        }

    companion object {
        private val FOOD_ITEMS_LIST = listOf(
            FoodItem(R.drawable.ic_icon_apple, true),
            FoodItem(R.drawable.ic_icon_book, false),
            FoodItem(R.drawable.ic_icon_burger, true),
            FoodItem(R.drawable.ic_icon_cake, true),
            FoodItem(R.drawable.ic_icon_chair, false),
            FoodItem(R.drawable.ic_icon_chicken_leg, true),
            FoodItem(R.drawable.ic_icon_congee, true),
            FoodItem(R.drawable.ic_icon_fish, true),
            FoodItem(R.drawable.ic_icon_hot_dog, true),
            FoodItem(R.drawable.ic_icon_levels, false),
            FoodItem(R.drawable.ic_icon_microphone, false),
            FoodItem(R.drawable.ic_icon_milk, true),
            FoodItem(R.drawable.ic_icon_pizza, true),
            FoodItem(R.drawable.ic_icon_poison, false),
            FoodItem(R.drawable.ic_icon_radio, false),
            FoodItem(R.drawable.ic_icon_satellite_dish, false),
            FoodItem(R.drawable.ic_icon_smartphone, false),
            FoodItem(R.drawable.ic_icon_video_camera, false),
        )
    }
}