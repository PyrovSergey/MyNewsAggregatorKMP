package com.example.mynewsaggregator

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import com.example.mynewsaggregator.data.di.appModule
import com.example.mynewsaggregator.presentation.navigation.AppNavGraph
import org.koin.compose.KoinApplication

@Composable
fun App() {
    KoinApplication(application = {
        modules(appModule)
    }) {
        MaterialTheme {
            AppNavGraph()
        }
    }
}