package ge.gis.countdowninteraction

import android.media.audiofx.NoiseSuppressor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.aldebaran.qi.sdk.QiContext
import com.aldebaran.qi.sdk.QiSDK
import com.aldebaran.qi.sdk.RobotLifecycleCallbacks
import com.aldebaran.qi.sdk.design.activity.RobotActivity
import ge.gis.countdowninteraction.FragmenUnits.setFragment

class MainActivity : RobotActivity(),RobotLifecycleCallbacks {
    var TAG ="MainActivity"
    var countDownNoInteraction :CountDownNoInteraction? = null
    private var qiContext :QiContext? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FragmenUnits.fragmentManager = supportFragmentManager
        setFragment(MainFragment())
        QiSDK.register(this,this)
    }

    override fun onStart() {
        super.onStart()
        countDownNoInteraction = CountDownNoInteraction(
                NoInteraction(),
                30000, 10000
        )
        countDownNoInteraction!!.start()
    }
    override fun onUserInteraction() {
        super.onUserInteraction()
        Log.i(TAG,"OnUserInteraction");
        countDownNoInteraction!!.reset()
    }
    override fun onRestart() {
        super.onRestart()
        setFragment(NoInteraction())

    }
    override fun onDestroy() {
        super.onDestroy()
        countDownNoInteraction!!.cancel()
        QiSDK.unregister(this,this)
    }


    override fun onRobotFocusGained(qiContext: QiContext?) {
        this.qiContext=qiContext
    }

    override fun onRobotFocusLost() {
    }

    override fun onRobotFocusRefused(reason: String?) {
        Log.i(TAG,"OnRobotFocusRefused Reason :: $reason")
    }

}