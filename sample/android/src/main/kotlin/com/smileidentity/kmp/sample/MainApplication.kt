package com.smileidentity.kmp.sample

import android.app.Application
import com.smileidentity.kmp.SmileIdentity

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initializeSmileIdentity()
    }

    private fun initializeSmileIdentity() {
        SmileIdentity.initialize(this)
    }

}