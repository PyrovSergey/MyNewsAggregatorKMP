package com.example.mynewsaggregator.presentation.screens.news_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynewsaggregator.data.model.NewsArticle
import com.example.mynewsaggregator.data.repository.NewsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NewsListViewModel(
    private val repository: NewsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<NewsUiState>(NewsUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val categoryCache = mutableMapOf<String?, CategoryData>()
    private var currentCategory: String? = null

    var isLoadingMore by mutableStateOf(false)
        private set

    init {
        loadNews()
    }

    fun loadNews(category: String? = null, isRefreshing: Boolean = false) {
        viewModelScope.launch {
            if (!isRefreshing && categoryCache.containsKey(category)) {
                val cached = categoryCache[category]!!
                _uiState.value = NewsUiState.Success(cached.articles)
                currentCategory = category
                return@launch
            }

            currentCategory = category
            if (isRefreshing) {
                categoryCache.remove(category)
            }

            _uiState.value = NewsUiState.Loading

            repository.getNews(
                category = category,
                page = 1,
                clearCache = true
            ).fold(
                onSuccess = { articles ->
                    categoryCache[category] = CategoryData(
                        articles = articles,
                        currentPage = 1,
                        hasMorePages = true
                    )
                    _uiState.value = NewsUiState.Success(articles)
                },
                onFailure = { error ->
                    _uiState.value = NewsUiState.Error(error.message ?: "Unknown error")
                }
            )
        }
    }

    fun loadMoreNews(category: String? = null) {
        if (isLoadingMore) return

        val categoryData = categoryCache[category] ?: return
        if (!categoryData.hasMorePages) return

        viewModelScope.launch {
            isLoadingMore = true
            val nextPage = categoryData.currentPage + 1

            repository.getNews(
                category = category,
                page = nextPage,
                clearCache = false
            ).fold(
                onSuccess = { newArticles ->
                    if (newArticles.isEmpty()) {
                        categoryCache[category] = categoryData.copy(hasMorePages = false)
                    } else {
                        val updatedArticles = categoryData.articles + newArticles
                        categoryCache[category] = CategoryData(
                            articles = updatedArticles,
                            currentPage = nextPage,
                            hasMorePages = true
                        )
                        _uiState.value = NewsUiState.Success(updatedArticles)
                    }
                    isLoadingMore = false
                },
                onFailure = { error ->
                    isLoadingMore = false
                }
            )
        }
    }
}

private data class CategoryData(
    val articles: List<NewsArticle>,
    val currentPage: Int,
    val hasMorePages: Boolean
)

sealed class NewsUiState {
    object Loading : NewsUiState()
    data class Success(val articles: List<NewsArticle>) : NewsUiState()
    data class Error(val message: String) : NewsUiState()
}