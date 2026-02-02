package com.example.mynewsaggregator.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.mynewsaggregator.presentation.screens.news_detail.NewsDetailScreen
import com.example.mynewsaggregator.presentation.screens.news_list.NewsListScreen

@Composable
fun AppNavGraph() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NewsListRoute
    ) {
        composable<NewsListRoute> {
            NewsListScreen(
                onNewsClick = { article ->
                    navController.navigate(NewsDetailRoute(article.uuid))
                }
            )
        }
         composable<NewsDetailRoute> { backStackEntry ->
             val args = backStackEntry.toRoute<NewsDetailRoute>()
             NewsDetailScreen(
                 uuid = args.uuid,
                 onBack = { navController.popBackStack() }
             )
         }
    }
}