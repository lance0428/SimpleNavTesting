package com.lance.simplenavtesting.core

import android.os.Bundle
import android.os.Parcelable

abstract class BaseKey(val modal: Boolean = false) : Parcelable {
    val fragmentTag: String
        get() = toString()

    fun newFragment(): BaseFragment = createFragment().apply {
        arguments = (arguments ?: Bundle()).also { bundle ->
            bundle.putParcelable("KEY", this@BaseKey)
        }
    }

    protected abstract fun createFragment(): BaseFragment
}