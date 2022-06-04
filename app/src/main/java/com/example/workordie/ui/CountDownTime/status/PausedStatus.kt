package com.example.workordie.ui.CountDownTime.status

import com.example.workordie.ui.CountDownTime.CountDownTimeViewModel

/**
 * Description:
 * Paused
 *
 * @author Alpinist Wang
 * Date:    2021/3/5
 */
class PausedStatus(private val viewModel: CountDownTimeViewModel) : IStatus {
    override fun startButtonDisplayString() = "Resume"

    override fun clickStartButton() = viewModel.animatorController.resume()

    override fun stopButtonEnabled() = true

    override fun clickStopButton() = viewModel.animatorController.stop()

    override fun showEditText() = false

    override fun progressSweepAngle() = viewModel.animValue / viewModel.totalTime * 360

    override fun completedString() = ""
}