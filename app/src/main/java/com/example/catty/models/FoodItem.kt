package com.example.catty.models

data class FoodItem(
    var image: Int = -1,
    var eatable: Boolean = false,
    var number: Int = 0,
    var uid: String = ""
)