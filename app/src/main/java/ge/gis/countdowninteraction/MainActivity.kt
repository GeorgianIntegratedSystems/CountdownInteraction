
package ge.gis.countdowninteraction

import android.content.Context
import android.os.Bundle
import android.speech.tts.UtteranceProgressListener
import android.util.Log
import android.widget.Button
import com.aldebaran.qi.sdk.QiContext
import com.aldebaran.qi.sdk.QiSDK
import com.aldebaran.qi.sdk.RobotLifecycleCallbacks
import com.aldebaran.qi.sdk.design.activity.RobotActivity
import ge.android.gis.pepperttslibrary.TTSUtils
import ge.gis.countdowninteraction.FragmenUnits.setFragment
import ge.gis.countdowninteraction.HumanInteractChange.Interaction
import kotlinx.coroutines.*

class MainActivity : RobotActivity(),RobotLifecycleCallbacks {
    var TAG ="MainActivity"
    var countDownNoInteraction : CountDownNoInteraction? = null
    private var humanEngager: HumanEngage? = null
    private var qiContext :QiContext? = null
    var Units : Units? = null
    companion object{
        var tts: TTSUtils? = null
    }

    init {
        Units = Units()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FragmenUnits.fragmentManager = supportFragmentManager
        QiSDK.register(this, this)
        setFragment(NoInteraction())
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
        Interaction = true
        Log.i(TAG, "OnUserInteraction : Interaction value $Interaction");
        countDownNoInteraction!!.reset()
    }
    override fun onRestart() {
        super.onRestart()
        Interaction = false
    }
    override fun onDestroy() {
        super.onDestroy()
        countDownNoInteraction!!.cancel()
        humanEngager!!.humanAwareness!!.removeAllOnEngagedHumanChangedListeners();
        QiSDK.unregister(this, this)
    }

    @DelicateCoroutinesApi
    override fun onRobotFocusGained(qiContext: QiContext?) {
        this.qiContext=qiContext
        humanEngager = HumanEngage(qiContext!!)
        Units!!.start()
        humanEngager!!.start()
        GlobalScope.launch(Dispatchers.Default) {
             async{ initTTS() }.await()
//            async { ttsListener() }.await()
        }

    }
    override fun onRobotFocusLost() {
        humanEngager!!.humanAwareness!!.removeAllOnEngagedHumanChangedListeners();
    }
    override fun onRobotFocusRefused(reason: String?) {
        Log.i(TAG, "OnRobotFocusRefused Reason :: $reason")
    }
    private suspend fun initTTS() {
        withContext(Dispatchers.IO) {
            tts = TTSUtils(this@MainActivity, qiContext!!)
      } }




//    private suspend fun ttsListener(){
//         withContext(Dispatchers.IO) {
//            tts!!.tts!!.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
//                override fun onStart(utteranceId: String) {
//                    Log.i("TAG", "onStart: ")
//                    Interaction = true
//                }
//
//                override fun onError(utteranceId: String) {
//                    Log.i("TAG", "onError: $utteranceId ")
//                }
//
//                override fun onDone(utteranceId: String) {
//                    Log.i("TAG", "onDone: ")
//                    Interaction = true
//                }
//            })
//        } }
}