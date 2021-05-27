package ge.gis.countdowninteraction

import android.util.Log
import com.aldebaran.qi.sdk.QiContext
import com.aldebaran.qi.sdk.`object`.humanawareness.HumanAwareness
import ge.gis.countdowninteraction.FragmenUnits.getFragment


class HumanEngage(private val qiContext: QiContext) {
    var humanAwareness: HumanAwareness? = null

    fun start(){
        humanAwareness = qiContext.humanAwareness;
        humanAwareness!!.async().addOnEngagedHumanChangedListener {
            Log.i("HumanEngage","Human Awareness AddOnEngageChange")
            if (getFragment()!!.javaClass.simpleName == "NoInteraction") {
                Log.i("HumanEngage","Human Awareness AddOnEngageChange IF")
                HumanInteractChange.Interaction = true;
            }
        }

    }

        }