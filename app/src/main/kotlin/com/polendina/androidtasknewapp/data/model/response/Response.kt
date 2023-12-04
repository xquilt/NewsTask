package com.polendina.androidtasknewapp.data.model.response

import com.polendina.androidtasknewapp.domain.model.Publication

data class Response(
    val status: String?,
    val totalResults: Int?,
    val articles: List<Publication>?
)
