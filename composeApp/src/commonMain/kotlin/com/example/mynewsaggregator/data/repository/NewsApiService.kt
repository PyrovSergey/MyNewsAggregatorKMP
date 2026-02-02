package com.example.mynewsaggregator.data.repository

import com.example.mynewsaggregator.data.model.NewsResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class NewsApiService(private val client: HttpClient) {

    private val baseUrl = "https://api.thenewsapi.com/v1"
    private val apiToken = "0focnluN9XMfRu2K36WVs4LylR8YntlGlBNJbmHV"

    suspend fun getAllNews(
        language: String = "en",
        categories: String? = null,
        page: Int = 1,
        limit: Int = 30
    ): Result<NewsResponse> {
        return try {
            val response = client.get("$baseUrl/news/all") {
                parameter("api_token", apiToken)
                parameter("language", language)
                parameter("page", page)
                parameter("limit", limit)
                categories?.let { parameter("categories", it) }
            }.body<NewsResponse>()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}