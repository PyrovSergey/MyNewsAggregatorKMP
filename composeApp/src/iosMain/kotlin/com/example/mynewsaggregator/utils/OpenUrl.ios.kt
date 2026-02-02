package com.example.mynewsaggregator.utils

import platform.Foundation.NSDictionary
import platform.Foundation.NSURL
import platform.UIKit.UIApplication

actual fun openUrl(url: String) {
    val nsUrl = NSURL.URLWithString(url)

    if (nsUrl != null) {
        UIApplication.sharedApplication.openURL(
            nsUrl,
            @Suppress("UNCHECKED_CAST")
            (NSDictionary()) as Map<Any?, *>,
            null
        )
    } else {
        println("Error: Invalid URL: $url")
    }
}