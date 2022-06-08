package com.example.wildberriesweeksixflow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.random.Random


class FragmentViewModel : ViewModel() {

    val randomValue = MutableStateFlow<Int?>(3)
    var i = 0
    val count = MutableStateFlow<Int>(0)
    private var job: Job? = null
    var isCount = false
    private var isPaused = false
    var randomNumber: Int? = null

    fun startSeconds() {
        viewModelScope.launch(Dispatchers.IO) {
            while (isActive) {

                if (!isPaused) {
                    delay(1000)
                    count.value = i++
                }
            }

        }

        viewModelScope.launch(Dispatchers.IO) {
            while (isActive) {
                if (!isPaused) {
                    randomNumber = addRandomNumber()
                    randomValue.value = randomNumber
                }
            }

        }
    }


    fun play() {
        isPaused = false
    }

    fun pause() {
        isPaused = true
    }

    fun reset() {
        pause()
        randomNumber = 10
        randomValue.value = randomNumber
        i = 0
        count.value = i
    }


    private fun addRandomNumber(): Int? {
        return Random.nextInt(1, 9)
    }


}

