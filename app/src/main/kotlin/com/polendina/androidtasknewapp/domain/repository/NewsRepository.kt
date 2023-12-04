package com.polendina.androidtasknewapp.domain.repository

import com.polendina.androidtasknewapp.data.model.response.NewsEverything
import com.polendina.androidtasknewapp.data.model.response.NewsSources
import com.polendina.androidtasknewapp.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsRepository {
    /**
     * Search through millions of articles from over 80,000 large and small news sources and blogs.
     *
     * @param apiKey Your API key. Alternatively you can provide this via the X-Api-Key HTTP header.
     * @param q Keywords or phrases to search for in the article title and body.
                Advanced search is supported here:
                    Surround phrases with quotes (") for exact match.
                    Prepend words or phrases that must appear with a + symbol. Eg: +bitcoin
                    Prepend words that must not appear with a - symbol. Eg: -bitcoin
                    Alternatively you can use the AND / OR / NOT keywords, and optionally group these with parenthesis. Eg: crypto AND (ethereum OR litecoin) NOT bitcoin.
                The complete value for q must be URL-encoded. Max length: 500 chars.
     */
    @GET("/v2/everything")
    suspend fun getNewsEverything(
        @Query("apiKey") apiKey: String = Constants.API_KEY,
        @Query("q") searchQuery: String,
//        @Query("pageSize") pageSize: Int = 100,
    ): Response<NewsEverything>
    /**
     *
     *
     * @param apiKey Your API key. Alternatively you can provide this via the X-Api-Key HTTP header.
     * @param category The category you want to get headlines for. Note: you can't mix this param with the sources param.
     * @param country The 2-letter ISO 3166-1 code of the country you want to get headlines for.
     */
    @GET("/v2/top-headlines/sources")
    suspend fun newsSources(
        @Query("apiKey") apiKey: String = Constants.API_KEY,
        @Query("category") category: String = Constants.Categories.TECHNOLOGY.title,
        @Query("country") country: String = Constants.countries.first(),
        @Query("language") language: String = Constants.Languages.ENGLISH.code
    ): Response<NewsSources>

    /**
     * This endpoint provides live top and breaking headlines for a country, specific category in a country, single source, or multiple sources.
     *
     * @param apiKey Your API key. Alternatively you can provide this via the X-Api-Key HTTP header.
     * @param country The 2-letter ISO 3166-1 code of the country you want to get headlines for.
                      Note: you can't mix this param with the sources param.
     * @param category The category you want to get headlines for. Note: you can't mix this param with the sources param.
     * @param sources A comma-separated string of identifiers for the news sources or blogs you want headlines from.
                     Use the /top-headlines/sources endpoint to locate these programmatically or look at the sources index.
                     Note: you can't mix this param with the country or category params.
     * @param q Keywords or a phrase to search for.
     * @param pageSize The number of results to return per page (request). 20 is the default, 100 is the maximum.
     */
    @GET("/v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("apiKey") apiKey: String = Constants.API_KEY,
        @Query("q") searchQuery: String,
        @Query("sources") sources: String = "",
        @Query("country") country: String = Constants.countries.first(),
        @Query("category") category: String = Constants.Categories.TECHNOLOGY.title,
        @Query("pageSize") pageSize: Int = 100,
    ): Response<NewsEverything>
}