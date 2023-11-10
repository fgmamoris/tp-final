package com.ifts4.tpfinal

import android.app.Application

class TpFinal : Application() {
    //Es como el static de java
    companion object {
        lateinit var instance: TpFinal
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}