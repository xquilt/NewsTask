package com.polendina.androidtasknewapp.ui.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.polendina.androidtasknewapp.domain.model.Publication
import com.polendina.androidtasknewapp.domain.repository.FakeNewsRepository
import com.polendina.androidtasknewapp.domain.repository.NewsRepository
import com.polendina.androidtasknewapp.utils.Constants
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

interface HomeScreenViewModel {
    val newsFeed: SnapshotStateList<Publication>
    var searchQuery: MutableState<String>
    val categories: List<Constants.Categories>
    fun searchArticles(searchQuery: String): Job
}

class HomeScreenViewModelImpl(
    private val newsRepository: NewsRepository,
    val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO
): HomeScreenViewModel {
    private val scope = CoroutineScope(coroutineDispatcher)
    private val _newsFeed: SnapshotStateList<Publication> = mutableStateListOf()
    override val newsFeed = _newsFeed
    private var _searchQuery = mutableStateOf("")
    override var searchQuery: MutableState<String> = _searchQuery
    override val categories: List<Constants.Categories>
        get() = Constants.Categories.entries
    override fun searchArticles(searchQuery: String): Job = scope.launch {
        _newsFeed.addAll(
    async { newsRepository.getNewsEverything(searchQuery = searchQuery) }
            .await()
            .also { if(it.isSuccessful) _newsFeed.clear() }
            .body()?.articles ?: emptyList()
        )
    }

    init {
        scope.launch {
            _newsFeed.addAll(
                scope.async { newsRepository.getTopHeadlines(searchQuery = "").body()?.articles ?: emptyList() }.await()
            )
        }
    }
}

class HomeScreenViewModelMock(
    private val newsRepository: NewsRepository = FakeNewsRepository()
): HomeScreenViewModel {
    private val _newsFeed: SnapshotStateList<Publication> = mutableStateListOf()
    override val newsFeed = _newsFeed
    private val scope = CoroutineScope(Dispatchers.IO)
    override fun searchArticles(searchQuery: String): Job = scope.launch {  }
    override val categories: List<Constants.Categories>
        get() = Constants.Categories.entries
    override var searchQuery: MutableState<String>
        get() = mutableStateOf("")
        set(value) {}
    init {
        _newsFeed.addAll(FakeNewsRepository().publications)
    }
}