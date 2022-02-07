package com.example.catty.ui.screens.pause

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.catty.prefs.GameRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GameOverFragmentViewModel @Inject constructor(
    private val gameRepository: GameRepository
): ViewModel() {

    private val _highestScore: MutableLiveData<Int> = MutableLiveData()
    val highestScore: LiveData<Int> = _highestScore

    init {
        _highestScore.postValue(gameRepository.highestScore)
    }

    fun updateHighestScore(score: Int) {
        gameRepository.highestScore = score
    }
}