package com.example.workordie.ui.CountDownTime.status
import com.example.workordie.ui.CountDownTime.CountDownTimeViewModel

/**
 * Description:
 * Started
 *
 * @author Alpinist Wang
 * Date:    2021/3/5
 */
class StartedStatus(private val viewModel: CountDownTimeViewModel) : IStatus {
    override fun startButtonDisplayString() = "Pause"

    override fun clickStartButton() = viewModel.animatorController.pause()

    override fun stopButtonEnabled() = true

    override fun clickStopButton() = viewModel.animatorController.stop()

    override fun showEditText() = false

    override fun progressSweepAngle() = viewModel.animValue / viewModel.totalTime * 360

    override fun completedString() = ""
}