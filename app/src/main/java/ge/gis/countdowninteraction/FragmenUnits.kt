package ge.gis.countdowninteraction

import android.os.Bundle
import android.provider.SyncStateContract
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

object FragmenUnits {

        var fragmentManager: FragmentManager? = null
        fun setFragment(fragment: Fragment) {
            Log.d("Fragment Units", "Transaction for fragment : " + fragment.javaClass.simpleName)
            val transaction: FragmentTransaction = fragmentManager!!.beginTransaction()
            transaction.setCustomAnimations(
                    R.anim.enter_fade_in_right,
                    R.anim.exit_fade_out_left,
                    R.anim.enter_fade_in_left,
                    R.anim.exit_fade_out_right
            )

            transaction.replace(R.id.fragmentSetter, fragment, "currentFragment")
            transaction.addToBackStack(null)
            transaction.commit()
        }

        fun getFragment(): Fragment? {
            return fragmentManager!!.findFragmentByTag("currentFragment")
        }
}