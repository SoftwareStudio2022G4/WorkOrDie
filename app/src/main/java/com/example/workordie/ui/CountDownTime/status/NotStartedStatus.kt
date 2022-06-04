package com.example.workordie.ui.CountDownTime.status

import com.example.workordie.ui.CountDownTime.CountDownTimeViewModel


/**
 * Description:
 * Not Started
 *
 * @author Alpinist Wang
 * Date:    2021/3/5
 */
class NotStartedStatus(private val viewModel: CountDownTimeViewModel) : IStatus {

    override fun startButtonDisplayString() = "Start"

    override fun clickStartButton() = viewModel.animatorController.start()

    override fun stopButtonEnabled() = false

    override fun clickStopButton() {}

    override fun showEditText() = true

    override fun progressSweepAngle() = if (viewModel.totalTime > 0) 360f else 0f

    override fun completedString() = ""
}