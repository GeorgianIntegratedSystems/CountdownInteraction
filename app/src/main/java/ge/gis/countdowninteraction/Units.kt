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
               Log.i("TAG", "Equals True IF")
           } else {
               Log.i("TAG", "Equals True ELSE")
               FragmenUnits.setFragment(MainFragment())
           }
       } else {
           if (FragmenUnits.getFragment()!!.javaClass.simpleName == "NoInteraction") {
               Log.i("TAG", "Equals false IF")
           } else {
               Log.i("TAG", "Equals false ELSE")
               FragmenUnits.setFragment(NoInteraction())
           }
       }
   }
}
