package com.example.mynewsaggregator.data.repository

import com.example.mynewsaggregator.data.model.NewsArticle

class NewsRepository(private val apiService: NewsApiService) {

    private val cachedArticles = mutableListOf<NewsArticle>()

    suspend fun getNews(
        language: String = "ru",
        category: String? = null,
        page: Int = 1,
        clearCache: Boolean = false
    ): Result<List<NewsArticle>> {

        if (clearCache) {
            cachedArticles.clear()
        }

        return apiService.getAllNews(
            language = language,
            categories = category,
            page = page
        ).map { response ->
            if (page == 1) {
                cachedArticles.clear()
            }
            cachedArticles.addAll(response.data)
            cachedArticles.toList()
        }
    }

    fun getArticlesByUuid(uuid: String): NewsArticle? {
        return cachedArticles.find { it.uuid == uuid }
    }
}