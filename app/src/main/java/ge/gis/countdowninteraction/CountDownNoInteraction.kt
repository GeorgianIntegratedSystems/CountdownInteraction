package ge.gis.countdowninteraction

import android.os.CountDownTimer
import android.util.Log
import androidx.fragment.app.Fragment
import ge.gis.countdowninteraction.FragmenUnits.setFragment

class CountDownNoInteraction(
        fragmentToSet: Fragment,
        millisUtilEnd: Long,
        countDownInterval: Long
) :
        CountDownTimer(millisUtilEnd, countDownInterval) {
    private val TAG = "MSI_NoInteraction"
    private val fragment: Fragment = fragmentToSet
    override fun onTick(millisUntilFinished: Long) {
    }

    override fun onFinish() {
        Log.d(TAG, "Timer Finished")
        setFragment(fragment)
    }

    fun reset() {
        Log.d(TAG, "Timer Reset")
        super.cancel()
        super.start()
    }

}