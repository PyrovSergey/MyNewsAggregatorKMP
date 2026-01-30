package com.example.mynewsaggregator

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform