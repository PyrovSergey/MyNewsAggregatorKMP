package com.example.mynewsaggregator.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsResponse(
    val meta: Meta,
    val data: List<NewsArticle>
)

@Serializable
data class Meta(
    val found: Int,
    val returned: Int,
    val limit: Int,
    val page: Int
)

@Serializable
data class NewsArticle(
    val uuid: String,
    val title: String,
    val description: String,
    val keywords: String? = null,
    val snippet: String? = null,
    val url: String,
    @SerialName("image_url")
    val imageUrl: String? = null,
    val language: String,
    @SerialName("published_at")
    val publishedAt: String,
    val source: String,
    val categories: List<String>
)