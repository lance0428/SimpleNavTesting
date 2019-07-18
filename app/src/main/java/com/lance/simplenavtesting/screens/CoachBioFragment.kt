package com.lance.simplenavtesting.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lance.simplenavtesting.R
import com.lance.simplenavtesting.core.BaseFragment
import com.lance.simplenavtesting.core.BaseKey
import kotlinx.android.parcel.Parcelize

/**
 * Created by Owner on 2017.11.13.
 */

class CoachBioFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.coach_bio_view, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // ...
    }
}

@Parcelize
data class CoachBioKey(private val placeholder: String = "") : BaseKey() { // generate reliable `toString()` for no-args data class
    override fun createFragment() = VideoPreviewFragment()
}