package com.example.catty.ui.util

class CenterItemHelper(
    val leftBorder: Int,
    val rightBorder: Int
) {
    fun ifItemInCenter(itemLeft: Int,itemRight: Int): Boolean {
        return leftBorder < itemLeft && rightBorder > itemRight
    }
}