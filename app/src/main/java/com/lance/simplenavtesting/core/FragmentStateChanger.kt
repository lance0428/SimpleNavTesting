package com.lance.simplenavtesting.core

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.lance.simplenavtesting.R
import com.zhuinden.simplestack.StateChange

class FragmentStateChanger(
    private val fragmentManager: FragmentManager,
    private val containerId: Int
) {
    fun handleStateChange(stateChange: StateChange) {
        val fragmentTransaction = fragmentManager.beginTransaction().apply {
            when (stateChange.direction) {
                StateChange.FORWARD -> {
                    setCustomAnimations(
                        R.anim.slide_in_from_right,
                        R.anim.slide_out_to_left,
                        R.anim.slide_in_from_right,
                        R.anim.slide_out_to_left
                    )
                }
                StateChange.BACKWARD -> {
                    val removedView = stateChange.getPreviousKeys<BaseKey>().last()
                    if (removedView.modal) {
                        setCustomAnimations(
                            0,
                            R.anim.abc_slide_out_bottom
                        )
                    } else {
                        setCustomAnimations(
                            R.anim.slide_in_from_left,
                            R.anim.slide_out_to_right,
                            R.anim.slide_in_from_left,
                            R.anim.slide_out_to_right
                        )
                    }
                }
                StateChange.REPLACE -> {
                    setCustomAnimations(
                        R.anim.abc_slide_in_bottom,
                        R.anim.abc_fade_out
                    )
                }
            }
            val previousState = stateChange.getPreviousKeys<BaseKey>()
            val newState = stateChange.getNewKeys<BaseKey>()
            for (oldKey in previousState) {
                val fragment = fragmentManager.findFragmentByTag(oldKey.fragmentTag)
                if (fragment != null) {
                    if (!newState.contains(oldKey)) {
                        remove(fragment)
                    } else if (!fragment.isDetached) {
                        detach(fragment)
                    }
                }
            }
            for (newKey in newState) {
                var fragment: Fragment? = fragmentManager.findFragmentByTag(newKey.fragmentTag)
                if (newKey == stateChange.topNewKey<Any>()) {
                    if (fragment != null) {
                        if (fragment.isRemoving) { // Fragments are quirky, they die asynchronously. Ignore if they're still there.
                            replace(containerId, newKey.newFragment(), newKey.fragmentTag)
                        } else if (fragment.isDetached) {
                            attach(fragment)
                        }
                    } else {
                        fragment = newKey.newFragment()
                        add(containerId, fragment, newKey.fragmentTag)
                    }
                } else {
                    if (fragment != null && !fragment.isDetached) {
                        detach(fragment)
                    }
                }
            }
        }
        fragmentTransaction.commitAllowingStateLoss()
    }
}