package com.example.demokmp

class JvmPlatform : Platform {
    override val name: String = "JvmPlatform"
}

actual fun getPlatform(): Platform {
    return JvmPlatform()
}