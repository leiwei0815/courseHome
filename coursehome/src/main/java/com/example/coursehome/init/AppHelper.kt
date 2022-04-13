package com.example.coursehome.init

import android.content.Context
import android.net.ConnectivityManager

const val BASEURL = "https://ntcapi-fat.shiguangkey.com/api/app/"

val Context.isConnectedNetwork: () -> Boolean
    get() = {
       val manager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        manager.activeNetworkInfo?.isAvailable?:false
    }


object AppHelper {
    lateinit var mContext: Context

    fun init(context: Context) {
        this.mContext = context
    }
}