package ge.gis.countdowninteraction

import android.util.Log
import ge.gis.countdowninteraction.HumanInteractChange.Interaction
import kotlinx.coroutines.*
import kotlin.concurrent.thread
import kotlin.properties.Delegates


class Units {

    fun start(){
        HumanInteractChange.refreshListListeners.add(object : HumanInteractChange.InterfaceRefreshList {
            override fun refreshListRequest() {
                runBlocking {
                    val scope = CoroutineScope(Dispatchers.IO)
                    scope.launch {
                        interactingHuman()
                    }
                }
                Log.i("TAG","CHANGED $Interaction")
            }
        })
    }

    fun interactingHuman() {
        if (Interaction) {
            if (FragmenUnits.getFragment()!!.javaClass.simpleName == "MainFragment") {
                Log.i("TAG", "Interaction : $Interaction , Fragment : MainFragment")
            } else {
                Log.i("TAG", "Interaction : $Interaction , Fragment : Changed To MainFragment")
                FragmenUnits.setFragment(MainFragment())
            }
        } else {
            if (FragmenUnits.getFragment()!!.javaClass.simpleName == "NoInteraction") {
                Log.i("TAG", "Interaction : $Interaction , Fragment : NoInteraction")
            } else {
                Log.i("TAG", "Interaction : $Interaction , Fragment : Changed To NoInteraction")
                FragmenUnits.setFragment(NoInteraction())
            }
        }
    }
}