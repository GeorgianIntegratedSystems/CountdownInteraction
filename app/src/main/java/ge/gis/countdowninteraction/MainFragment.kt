package ge.gis.countdowninteraction

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import ge.gis.countdowninteraction.FragmenUnits.setFragment
import ge.gis.countdowninteraction.MainActivity.Companion.tts

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        Toast.makeText(activity,"Fragment Main", Toast.LENGTH_LONG).show()
        Log.i("Activity", "Fragment Main")
        view.setOnClickListener {
            setFragment(NoInteraction())
        }
                tts!!.speakOut("გამარჯობა,! რით შემიძლია დაგეხმაროთ?")




        return view
    }

}