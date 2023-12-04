package com.polendina.androidtasknewapp.di

import com.polendina.androidtasknewapp.domain.repository.NewsRepository
import com.polendina.androidtasknewapp.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Networking {
    private val retrofit by lazy {
        Retrofit
            .Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api by lazy {
        retrofit
            .create(NewsRepository::class.java)
    }
}