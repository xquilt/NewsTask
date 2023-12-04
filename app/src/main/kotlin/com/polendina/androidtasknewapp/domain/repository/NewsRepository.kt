package com.polendina.androidtasknewapp.domain.repository

import com.polendina.androidtasknewapp.domain.model.Publication

interface News {
    suspend fun getNews(searchTerm: String): Result<List<Publication>>
}