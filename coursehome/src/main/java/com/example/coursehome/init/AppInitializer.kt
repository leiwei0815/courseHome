package com.example.coursehome.init

import android.content.Context
import androidx.startup.Initializer

class AppInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        AppHelper.init(context)
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }
}