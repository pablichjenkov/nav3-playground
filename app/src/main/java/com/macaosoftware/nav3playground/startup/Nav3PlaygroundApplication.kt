package com.macaosoftware.nav3playground.startup

import android.app.Application

class Nav3PlaygroundApplication : Application() {

    val appStartupManager = AppStartupManagerImpl

    override fun onCreate() {
        super.onCreate()
        appStartupManager.applicationStart()
    }
}
