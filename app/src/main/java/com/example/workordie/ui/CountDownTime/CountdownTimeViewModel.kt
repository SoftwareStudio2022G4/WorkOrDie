package com.example.workordie.ui.CountDownTime
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.workordie.ui.CountDownTime.status.CompletedStatus
import com.example.workordie.ui.CountDownTime.status.IStatus
import com.example.workordie.ui.CountDownTime.status.NotStartedStatus

/**
 * Description:
 * ViewModel of countdown timer
 *
 * @author Alpinist Wang
 * Date:    2021/3/4
 */
// Max input length limit, it's used to prevent number grows too big.
const val MAX_INPUT_LENGTH = 5

class CountDownTimeViewModel : ViewModel() {

    /**
     * Total time user set in seconds
     */
    var totalTime: Long by mutableStateOf(0)

    /**
     * Time left during countdown in seconds
     */
    var timeLeft: Long by mutableStateOf(0)

    var animatorController = AnimatorController(this)

    var status: IStatus by mutableStateOf(NotStartedStatus(this))

    var totalTimeSpent: Long = 0

    /**
     * Temp value when anim is active
     */
    var animValue: Float by mutableStateOf(0.0f)

    /**
     * Update value when EditText content changed
     * @param text new content in EditText
     */
    fun updateValue(text: String) {
        // Just in case the number is too big
        if (text.length > MAX_INPUT_LENGTH) return
        // Remove non-numeric elements
        var value = text.replace("\\D".toRegex(), "")
        // Zero cannot appear in the first place
        if (value.startsWith("0")) value = value.substring(1)
        // Set a default value to prevent NumberFormatException
        if (value.isBlank()) value = "0"
        totalTime = value.toLong()
        timeLeft = value.toLong()
        // After user clicks EditText, CompletedStatus turns to NotStartedStatus.
        if (status is CompletedStatus) status = NotStartedStatus(this)
    }

    fun recordTimeSpent() {
        if (timeLeft != 0L) {

        }
        totalTimeSpent += totalTime - timeLeft
        Log.d("ABCD", "totalTimeSpent = ${totalTimeSpent}")
    }
}