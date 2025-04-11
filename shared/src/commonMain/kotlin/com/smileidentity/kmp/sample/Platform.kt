package com.smileidentity.kmp.sample

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform