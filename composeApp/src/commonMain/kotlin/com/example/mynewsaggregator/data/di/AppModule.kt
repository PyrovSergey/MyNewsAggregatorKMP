package com.example.mynewsaggregator.data.di

import com.example.mynewsaggregator.data.remote.provideHttpClient
import com.example.mynewsaggregator.data.repository.NewsApiService
import com.example.mynewsaggregator.data.repository.NewsRepository
import com.example.mynewsaggregator.presentation.news_detail.NewsDetailViewModel
import com.example.mynewsaggregator.presentation.screens.news_list.NewsListViewModel
import org.koin.dsl.module

val appModule = module {

    // Network
    single { provideHttpClient() }
    single { NewsApiService(get()) }

    // Repository
    single { NewsRepository(get()) }

    // ViewModels
    factory { NewsListViewModel(get()) }
    factory { NewsDetailViewModel(get()) }
}