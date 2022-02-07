package com.example.catty.ui.util

class GameHelper {

    fun evalauteProgress(score: Int): Float {
        val num = score % ITEMS_TO_RISE_LEVEL
        val progress = num * (MAX_PROGRES / ITEMS_TO_RISE_LEVEL)
        return if (score % 5 == 0 && score != 0) {
            MAX_PROGRES
        } else {
            progress
        }
    }

    companion object {
        private const val ITEMS_TO_RISE_LEVEL = 5
        private const val MAX_PROGRES = 100f
    }
}