package ge.gis.countdowninteraction

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import ge.gis.countdowninteraction.FragmenUnits.setFragment
import ge.gis.countdowninteraction.HumanInteractChange.Interaction


class NoInteraction : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_no_interaction, container, false)
//        Interaction = false
        Toast.makeText(activity,"Fragment No Interaction",Toast.LENGTH_LONG).show()
        Log.i("Activity", "Fragment No Interaction")
        view.setOnClickListener {
               setFragment(MainFragment())
            }
        return view
    }

}