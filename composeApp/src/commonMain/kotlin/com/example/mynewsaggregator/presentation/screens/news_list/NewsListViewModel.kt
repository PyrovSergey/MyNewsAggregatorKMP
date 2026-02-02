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

    private var currentPage = 1

    var isLoadingMore by mutableStateOf(false)
        private set
    private var hasMorePages = true

    init {
        loadNews()
    }

    fun loadNews(category: String? = null, isRefreshing: Boolean = false) {
        viewModelScope.launch {

            if (isRefreshing) {
                currentPage = 1
                hasMorePages = true
            }

            _uiState.value = NewsUiState.Loading

            repository.getNews(
                category = category,
                page = currentPage,
                clearCache = isRefreshing
            ).fold(
                onSuccess = { articles ->
                    _uiState.value = NewsUiState.Success(articles)
                },
                onFailure = { error ->
                    _uiState.value = NewsUiState.Error(error.message ?: "Unknown error")
                }
            )
        }
    }

    fun loadMoreNews(category: String? = null) {
        if (isLoadingMore || !hasMorePages) return

        viewModelScope.launch {
            isLoadingMore = true
            currentPage++

            repository.getNews(
                category = category,
                page = currentPage
            ).fold(
                onSuccess = { articles ->
                    if (articles.isEmpty()) {
                        hasMorePages = false
                        currentPage--
                    }
                    _uiState.value = NewsUiState.Success(articles)
                    isLoadingMore = false
                },
                onFailure = { error ->
                    currentPage--
                    isLoadingMore = false
                }
            )
        }
    }
}

sealed class NewsUiState {
    object Loading : NewsUiState()
    data class Success(val articles: List<NewsArticle>) : NewsUiState()
    data class Error(val message: String) : NewsUiState()
}