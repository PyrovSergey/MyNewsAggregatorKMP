package com.example.mynewsaggregator.utils

import android.content.Context
import android.content.Intent
import android.net.Uri

private var appContext: Context? = null
fun initializeAndroidContext(context: Context) {
    appContext = context
}
actual fun openUrl(url: String) {
    val context = appContext ?: return

    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK
    }

    try {
        context.startActivity(intent)
    } catch (e: Exception) {
        println("Error opening URL: ${e.message}")
    }
}