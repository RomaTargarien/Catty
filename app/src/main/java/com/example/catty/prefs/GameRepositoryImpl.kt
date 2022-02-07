package com.example.catty.prefs

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class GameRepositoryImpl(private val context: Context) : GameRepository {

    private val PREFS_NAME = "prefs.db"
    private val sharedPref: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    private val KEY_USER_SCORE = "key.user.score"


    override var highestScore: Int = 0
        get() = sharedPref.getInt(KEY_USER_SCORE, 0)
        set(value) {
            val edit = sharedPref.edit()
            edit.putInt(KEY_USER_SCORE, value)
            edit.apply()
            field = value
        }
}