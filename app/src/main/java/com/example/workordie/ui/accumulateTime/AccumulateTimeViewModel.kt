package com.example.workordie.ui.accumulateTime

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.util.*
import kotlin.concurrent.fixedRateTimer
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime

@ExperimentalTime
class AccumulateTimeViewModel : ViewModel() {

    private var time: Duration = Duration.ZERO
    private lateinit var timer: Timer

    var seconds by mutableStateOf("00")
    var minutes by mutableStateOf("00")
    var hours by mutableStateOf("00")
    var isPlaying by mutableStateOf(false)

    var timeSpent by mutableStateOf(0L)

    fun start() {
        timer = fixedRateTimer(initialDelay = 1000L, period = 1000L) {
            time = time.plus(Duration.seconds(1))
            updateTimeStates()
        }
        isPlaying = true
    }

    private fun updateTimeStates() {
        time.toComponents { hours, minutes, seconds, _ ->
            this@AccumulateTimeViewModel.seconds = seconds.pad()
            this@AccumulateTimeViewModel.minutes = minutes.pad()
            this@AccumulateTimeViewModel.hours = hours.toString().padStart(2, '0')
        }
    }

    private fun Int.pad(): String {
        return this.toString().padStart(2, '0')
    }

    fun pause() {
        timer.cancel()
        isPlaying = false
    }

    fun stop() {
        pause()
        timeSpent += time.toLong(DurationUnit.SECONDS)
        Log.d("ABCD", "timeSpent = $timeSpent")
        time = Duration.ZERO
        updateTimeStates()
    }
}