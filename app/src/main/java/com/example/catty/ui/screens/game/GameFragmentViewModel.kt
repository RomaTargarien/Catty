package com.example.catty.ui.screens.game

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catty.R
import com.example.catty.models.FoodItem
import com.example.catty.ui.util.FoodUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import kotlin.math.ceil

@HiltViewModel
class GameFragmentViewModel @Inject constructor(): ViewModel() {

    private val _timeLeft: MutableLiveData<Int> = MutableLiveData()
    val timeLeft: LiveData<Int> = _timeLeft

    private var foodUtil: FoodUtil? = null

    private val _timeToPlay: MutableLiveData<Boolean> = MutableLiveData(false)
    val timeToPlay: LiveData<Boolean> = _timeToPlay

    val delayFlow: MutableStateFlow<Long> = MutableStateFlow(25L)
    val scrollDxFlow: MutableStateFlow<Int> = MutableStateFlow(5)
    val currentListFlow: MutableStateFlow<List<FoodItem>> = MutableStateFlow(mutableListOf())

    init {
        val timer = object : CountDownTimer(TIME_TO_START, 1000) {
            override fun onTick(p0: Long) {
                _timeLeft.postValue(ceil(p0.toDouble() / 1000).toInt())
            }

            override fun onFinish() {
                _timeToPlay.postValue(true)
            }
        }
        timer.start()
        foodUtil = FoodUtil()
    }

    fun newItemsNeeded() {
        viewModelScope.launch {
            foodUtil?.let {
                if (!it.currentListFoodItems.isEmpty()) {
                    currentListFlow.emit(it.currentListFoodItems)
                    it.generateRandomList()
                } else {
                    it.generateRandomList()
                    currentListFlow.emit(it.currentListFoodItems)
                }
            }
        }
    }

    fun speedUp() {
        viewModelScope.launch {
            delayFlow.emit(delayFlow.value + 10)
            scrollDxFlow.emit(scrollDxFlow.value + 10)
        }
    }

    companion object {
        private const val TIME_TO_START = 3000L
    }
}