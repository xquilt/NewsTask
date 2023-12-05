package com.polendina.androidtasknewapp.data.repository

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class NewsRepositoryImplTest {

    private lateinit var newsRepositoryImpl: NewsRepositoryImpl

    @BeforeEach
    fun setUp() {
        newsRepositoryImpl = NewsRepositoryImpl()
    }

    @AfterEach
    fun tearDown() {
    }

    @Test
    fun getNewsEverything() = runTest {
        assertEquals(
    "ok",
            newsRepositoryImpl.getTopHeadlines(searchQuery = "").body()?.status
        )
    }

    @Test
    fun newsSources() = runTest {
        assertEquals(
            "ok",
            newsRepositoryImpl.newsSources().body()?.status
        )
    }

    @Test
    fun getTopHeadlines() = runTest {
        assertEquals(
    "ok",
            newsRepositoryImpl.getTopHeadlines(searchQuery = "ok").body()?.status
        )
    }

}