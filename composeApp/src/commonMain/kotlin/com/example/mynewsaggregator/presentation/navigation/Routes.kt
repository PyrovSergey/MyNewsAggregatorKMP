package com.example.mynewsaggregator.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
object NewsListRoute

@Serializable
data class NewsDetailRoute(val uuid: String)