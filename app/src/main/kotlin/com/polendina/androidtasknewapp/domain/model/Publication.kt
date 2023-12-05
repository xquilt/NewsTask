package com.polendina.androidtasknewapp.domain.model

data class Publication(
    val id: Int? = null,
    val title: String?,
    val author: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?,
    val content: String?,
    val source: Source?
) {
    data class Source(
        val id: String?,
        val name: String?
    )
}