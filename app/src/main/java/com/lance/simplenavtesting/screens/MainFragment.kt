package com.lance.simplenavtesting.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.lance.simplenavtesting.R
import com.lance.simplenavtesting.core.BaseFragment
import com.lance.simplenavtesting.core.BaseKey
import com.zhuinden.simplestack.navigator.Navigator
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.main_view.*


/**
 * Created by Owner on 2017.11.13.
 */

class MainFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        inflater.inflate(R.layout.main_view, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        launchOnClick(ScheduleKey(), scheduleButton)
        launchOnClick(SettingsKey(), settingsButton)
        launchOnClick(VideoPreviewKey(), videoChatPreviewButton)
        launchOnClick(MessagesKey(), messagesButton)
        launchOnClick(CoachBioKey(), coachBioButton)

        val homeKey = getKey<MainKey>() // args
    }

    private fun launchOnClick(key: BaseKey, button: Button) {
        button.setOnClickListener {
            Navigator.getBackstack(activity).moveToTop(key, key.modal)
        }
    }
}

@Parcelize
data class MainKey(private val placeholder: String = "") :
    BaseKey() { // generate reliable `toString()` for no-args data class
    override fun createFragment() = MainFragment()
}