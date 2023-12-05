package com.polendina.androidtasknewapp.data.model.response

import com.polendina.androidtasknewapp.domain.model.NewsSource

data class NewsSources(
    val status: String,
    val sources: List<NewsSource>
)