package com.example.demokmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform