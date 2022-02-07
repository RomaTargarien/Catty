package com.example.catty.ui.screens.game

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catty.models.FoodItem
import com.example.catty.ui.util.food.FoodFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.ceil

@HiltViewModel
class GameFragmentViewModel @Inject constructor(
    private val foodFactory: FoodFactory
) : ViewModel() {

    private val _timeLeft: MutableLiveData<Int> = MutableLiveData()
    val timeLeft: LiveData<Int> = _timeLeft

    private val _timeToPlay: MutableLiveData<Boolean> = MutableLiveData(false)
    val timeToPlay: LiveData<Boolean> = _timeToPlay

    val delayFlow: MutableStateFlow<Long> = MutableStateFlow(35L)
    val scrollDxFlow: MutableStateFlow<Int> = MutableStateFlow(15)
    val currentListFlow: MutableStateFlow<List<FoodItem>> = MutableStateFlow(mutableListOf())

    var randomList: Deferred<List<FoodItem>>? = null

    private val _score: MutableLiveData<Int> = MutableLiveData(0)
    val score: LiveData<Int> = _score

    private val _livesLeft: MutableLiveData<Int> = MutableLiveData(3)
    val livesLeft: LiveData<Int> = _livesLeft

    private val _currentLevel: MutableLiveData<Int> = MutableLiveData(1)
    val currentLevel: LiveData<Int> = _currentLevel

    private var lastItemClicked: FoodItem? = null

    private var from = 0

    init {
        object : CountDownTimer(TIME_TO_START, 1000) {
            override fun onTick(p0: Long) {
                _timeLeft.postValue(ceil(p0.toDouble() / 1000).toInt())
            }

            override fun onFinish() {
                _timeToPlay.postValue(true)
            }
        }.start()
    }

    fun newItemsNeeded() {
        viewModelScope.launch {
            randomList?.let {
                asyncListWork(it)
                return@launch
            }
            asyncListWork(foodFactory.createRandomFoodItemsList(from, from + FOOD_LIST_SIZE))
        }
    }

    private suspend fun asyncListWork(asyncList: Deferred<List<FoodItem>>) {
        currentListFlow.emit(asyncList.await())
        from += FOOD_LIST_SIZE
        randomList = foodFactory.createRandomFoodItemsList(from, from + FOOD_LIST_SIZE)
    }

    fun trackGameProcess(foodItemInCenter: Boolean, itemClicked: FoodItem) {
        if (!(lastItemClicked == itemClicked)) {
            if (foodItemInCenter && itemClicked.eatable) {
                _score.postValue(_score.value!! + 1)
                if ((_score.value!! + 1) % 5 == 0) {
                    riseUpLevel()
                }
            } else {
                _livesLeft.postValue(_livesLeft.value!! - 1)
            }
            lastItemClicked = itemClicked
        }
    }

    fun riseUpLevel() {
        _currentLevel.postValue(_currentLevel.value!! + 1)
        speedUp()
    }

    private fun speedUp() {
        viewModelScope.launch {
            delayFlow.emit(delayFlow.value + 10)
            scrollDxFlow.emit(scrollDxFlow.value + 10)
        }
    }

    companion object {
        private const val FOOD_LIST_SIZE = 10
        private const val TIME_TO_START = 3000L
    }
}