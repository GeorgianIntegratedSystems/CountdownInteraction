package ge.gis.countdowninteraction

import android.os.Bundle
import android.util.Log
import com.aldebaran.qi.sdk.QiContext
import com.aldebaran.qi.sdk.QiSDK
import com.aldebaran.qi.sdk.RobotLifecycleCallbacks
import com.aldebaran.qi.sdk.`object`.conversation.Chat
import com.aldebaran.qi.sdk.`object`.conversation.QiChatbot
import com.aldebaran.qi.sdk.`object`.conversation.Topic
import com.aldebaran.qi.sdk.builder.ChatBuilder
import com.aldebaran.qi.sdk.builder.QiChatbotBuilder
import com.aldebaran.qi.sdk.builder.TopicBuilder
import com.aldebaran.qi.sdk.design.activity.RobotActivity
import ge.gis.countdowninteraction.FragmenUnits.getFragment
import ge.gis.countdowninteraction.FragmenUnits.setFragment
import ge.gis.countdowninteraction.HumanInteractChange.Interaction

class MainActivity : RobotActivity(),RobotLifecycleCallbacks {
    var TAG ="MainActivity"
    var countDownNoInteraction : CountDownNoInteraction? = null
    private var humanEngager: HumanEngage? = null
    private var qiContext :QiContext? = null
    var chat: Chat? = null
    var Units : Units? = null
    init {
        Units = Units()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FragmenUnits.fragmentManager = supportFragmentManager
        setFragment(MainFragment())
        QiSDK.register(this, this)
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
        Log.i(TAG, "OnUserInteraction");
        countDownNoInteraction!!.reset()
    }
    override fun onRestart() {
        super.onRestart()
        setFragment(NoInteraction())
        countDownNoInteraction!!.reset()
    }
    override fun onDestroy() {
        super.onDestroy()
        countDownNoInteraction!!.cancel()
        humanEngager!!.humanAwareness!!.removeAllOnEngagedHumanChangedListeners();
        QiSDK.unregister(this, this)

    }
    override fun onRobotFocusGained(qiContext: QiContext?) {
        this.qiContext=qiContext
        humanEngager = HumanEngage(qiContext!!)
        initChatbot()
        Units!!.start()
        humanEngager!!.start()
        chat!!.async()
            .addOnNormalReplyFoundForListener {
                if(getFragment()!!.javaClass.simpleName == "NoInteraction"){
                    Interaction = true
                }
                Log.i("TAG", "Robot Heard : $it ")
                countDownNoInteraction!!.reset()
            }
    }
    override fun onRobotFocusLost() {
        humanEngager!!.humanAwareness!!.removeAllOnEngagedHumanChangedListeners();

    }
    override fun onRobotFocusRefused(reason: String?) {
        Log.i(TAG, "OnRobotFocusRefused Reason :: $reason")

    }
    private fun initChatbot() {
        val topic: Topic = TopicBuilder.with(qiContext)
                .withResource(R.raw.main)
                .build()

        val qiChatbot: QiChatbot = QiChatbotBuilder.with(qiContext)
                .withTopic(topic)
                .build()


        chat = ChatBuilder.with(qiContext)
                .withChatbot(qiChatbot)
                .build()

        chat!!.async().run()
    }

}