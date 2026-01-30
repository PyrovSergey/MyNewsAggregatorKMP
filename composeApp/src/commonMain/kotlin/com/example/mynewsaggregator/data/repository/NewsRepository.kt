package com.example.mynewsaggregator.data.repository

import com.example.mynewsaggregator.data.model.NewsArticle

class NewsRepository(private val apiService: NewsApiService) {

    suspend fun getNews(
        language: String = "ru",
        category: String? = null,
        page: Int = 1
    ): Result<List<NewsArticle>> {
        return apiService.getAllNews(
            language = language,
            categories = category,
            page = page
        ).map { it.data }
    }
}