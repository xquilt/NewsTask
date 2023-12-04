package com.polendina.androidtasknewapp.di

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Networking {

    fun provideRetrofit(
        baseUrl: String
    ) = Retrofit
        .Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .build()

}