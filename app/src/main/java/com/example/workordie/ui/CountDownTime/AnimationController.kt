package com.example.workordie.ui.CountDownTime

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.view.animation.LinearInterpolator
import com.example.workordie.ui.CountDownTime.status.CompletedStatus
import com.example.workordie.ui.CountDownTime.status.NotStartedStatus
import com.example.workordie.ui.CountDownTime.status.PausedStatus
import com.example.workordie.ui.CountDownTime.status.StartedStatus
import com.example.workordie.ui.CountDownTime.utils.LogUtils
import kotlin.math.ceil

/**
 * Description:
 * Control ValueAnimator
 *
 * @author Alpinist Wang
 * Date:    2021/3/4
 */
// Control how many times the pointer is updated in a second
const val SPEED = 100

class AnimatorController(private val viewModel: CountDownTimeViewModel) {

    private var valueAnimator: ValueAnimator? = null

    fun start() {
        LogUtils.d("Start, totalTime: ${viewModel.totalTime}")
        if (viewModel.totalTime == 0L) return
        if (valueAnimator == null) {
            // Animator: totalTime -> 0
            valueAnimator = ValueAnimator.ofInt(viewModel.totalTime.toInt() * SPEED, 0)
            valueAnimator?.interpolator = LinearInterpolator()
            // Update timeLeft in ViewModel
            valueAnimator?.addUpdateListener {
                viewModel.animValue = (it.animatedValue as Int) / SPEED.toFloat()
                viewModel.timeLeft = ceil(it.animatedValue as Int / SPEED.toFloat()).toLong()
            }
            valueAnimator?.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    super.onAnimationEnd(animation)
                    complete()
                }
            })
        } else {
            valueAnimator?.setIntValues(viewModel.totalTime.toInt() * SPEED, 0)
        }
        // (LinearInterpolator + duration) aim to set the interval as 1 second.
        valueAnimator?.duration = viewModel.totalTime * 1000L
        valueAnimator?.start()
        viewModel.status = StartedStatus(viewModel)
    }

    fun pause() {
        LogUtils.d("Pause, totalTime: ${viewModel.totalTime}, timeLeft: ${viewModel.timeLeft}")
        valueAnimator?.pause()
        viewModel.status = PausedStatus(viewModel)
    }

    fun resume() {
        LogUtils.d("Resume, totalTime: ${viewModel.totalTime}, timeLeft: ${viewModel.timeLeft}")
        valueAnimator?.resume()
        viewModel.status = StartedStatus(viewModel)
    }

    fun stop() {
        LogUtils.d("Stop")
        valueAnimator?.cancel()
        viewModel.timeLeft = 0
        viewModel.status = NotStartedStatus(viewModel)
    }

    fun complete() {
        LogUtils.d("Complete")
        viewModel.totalTime = 0
        viewModel.status = CompletedStatus(viewModel)
    }
}