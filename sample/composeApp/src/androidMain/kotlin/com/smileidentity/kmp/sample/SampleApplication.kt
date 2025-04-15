package com.smileidentity.kmp.sample

import android.app.Application
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

class SampleApplication : Application() {
    @Override
    override fun onCreate() {
        super.onCreate()

        /** Napier initialization */
        Napier.base(DebugAntilog())
    }
}




