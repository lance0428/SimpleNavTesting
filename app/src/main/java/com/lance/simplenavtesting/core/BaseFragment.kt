package com.lance.simplenavtesting.core

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.View

open class BaseFragment : Fragment() {
    fun <T : BaseKey> getKey(): T = arguments!!.getParcelable<T>("KEY")


    override fun onAttach(context: Context) {
        log("onAttach")
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        log("onCreate hasSavedInstanceState=${savedInstanceState != null}")
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        log("onViewCreated hasSavedInstanceState=${savedInstanceState != null}")
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onStart() {
        log("onStart")
        super.onStart()
    }

    override fun onResume() {
        log("onResume")
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
        log("onPause")
    }

    override fun onStop() {
        super.onStop()
        log("onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        log("onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        log("onDestroy")
    }

    private fun log(message: String) {
        Log.e("LDJ", "$message - ${javaClass.simpleName} - ${hashCode()}")
    }


}