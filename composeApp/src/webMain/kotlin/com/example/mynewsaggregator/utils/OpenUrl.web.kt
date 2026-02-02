package com.example.mynewsaggregator.utils

import kotlin.js.ExperimentalWasmJsInterop
import kotlin.js.js

@OptIn(ExperimentalWasmJsInterop::class)
actual fun openUrl(url: String) {
    js("window.open(url, '_blank')")
}