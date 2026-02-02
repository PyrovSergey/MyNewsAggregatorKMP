package com.example.mynewsaggregator.presentation.news_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynewsaggregator.data.model.NewsArticle
import com.example.mynewsaggregator.data.repository.NewsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NewsDetailViewModel(
    private val repository: NewsRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<NewsDetailUiState>(NewsDetailUiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun loadArticle(uuid: String) {
        viewModelScope.launch {
            _uiState.value = NewsDetailUiState.Loading

            val article = repository.getArticleByUuid(uuid)

            if (article != null) {
                _uiState.value = NewsDetailUiState.Success(article)
            } else {
                _uiState.value = NewsDetailUiState.Error("Статья не найдена")
            }
        }
    }
}

sealed class NewsDetailUiState {
    object Loading : NewsDetailUiState()
    data class Success(val article: NewsArticle) : NewsDetailUiState()
    data class Error(val message: String) : NewsDetailUiState()
}