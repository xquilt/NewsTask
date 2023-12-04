package com.polendina.androidtasknewapp.domain.model

data class Publication(
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?,
    val content: String?,
    val source: Source
) {
    data class Source(
        val id: String?,
        val name: String?
    )
}