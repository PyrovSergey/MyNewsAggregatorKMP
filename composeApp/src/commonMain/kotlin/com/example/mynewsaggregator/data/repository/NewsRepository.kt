package com.example.mynewsaggregator.data.repository

import com.example.mynewsaggregator.data.model.NewsArticle

class NewsRepository(private val apiService: NewsApiService) {

    private val categoryCaches = mutableMapOf<String?, MutableList<NewsArticle>>()

    suspend fun getNews(
        language: String = "ru",
        category: String? = null,
        page: Int = 1,
        clearCache: Boolean = false
    ): Result<List<NewsArticle>> {

        val cache = categoryCaches.getOrPut(category) { mutableListOf() }

        if (clearCache) {
            cache.clear()
        }

        return apiService.getAllNews(
            language = language,
            categories = category,
            page = page
        ).map { response ->
            if (page == 1 && !clearCache) {
                cache.clear()
            }

            cache.addAll(response.data)
            cache.toList()
        }
    }

    fun getArticleByUuid(uuid: String): NewsArticle? {
        return categoryCaches.values
            .flatten()
            .find { it.uuid == uuid }
    }
}