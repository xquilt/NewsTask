package com.polendina.androidtasknewapp.data.repository

import com.polendina.androidtasknewapp.data.model.response.NewsEverything
import com.polendina.androidtasknewapp.data.model.response.NewsSources
import com.polendina.androidtasknewapp.di.Networking
import com.polendina.androidtasknewapp.domain.repository.NewsRepository
import retrofit2.Response

class NewsRepositoryImpl: NewsRepository {
    override suspend fun getNewsEverything(
        apiKey: String,
        searchQuery: String
    ): Response<NewsEverything> {
        return Networking.api.getNewsEverything(searchQuery = searchQuery)
    }

    override suspend fun newsSources(
        apiKey: String,
        category: String,
        country: String,
        language: String
    ): Response<NewsSources> {
        return Networking.api.newsSources()
    }

    override suspend fun getTopHeadlines(
        apiKey: String,
        searchQuery: String,
        sources: String,
        country: String,
        category: String,
        pageSize: Int
    ): Response<NewsEverything> {
        return Networking.api.getTopHeadlines(searchQuery = searchQuery)
    }
}