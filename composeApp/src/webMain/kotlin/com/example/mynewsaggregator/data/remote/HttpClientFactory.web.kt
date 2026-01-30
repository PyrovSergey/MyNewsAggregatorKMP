package com.example.mynewsaggregator.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.engine.js.*

actual fun createHttpClient(): HttpClient = HttpClient(Js)