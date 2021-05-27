package ge.gis.countdowninteraction

import android.util.Log
import kotlin.properties.Delegates

object HumanInteractChange {

    var refreshListListeners = ArrayList<InterfaceRefreshList>()

    var Interaction: Boolean by Delegates.observable(true) { _, _, newValue ->
        Log.i("VALUE Change New", "$newValue")
        refreshListListeners.forEach {
            it.refreshListRequest()
        }
    }
    interface InterfaceRefreshList {
        fun refreshListRequest()
    }
}