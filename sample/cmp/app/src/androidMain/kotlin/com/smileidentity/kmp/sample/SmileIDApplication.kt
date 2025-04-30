package com.smileidentity.kmp.sample

import android.app.Application
import com.smileidentity.kmp.PlatformDependency
import com.smileidentity.kmp.SmileID
import com.smileidentity.kmp.models.KmpConfig

class SmileIDApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        SmileID.initialize(
            context = PlatformDependency(context = this),
        )
    }
}