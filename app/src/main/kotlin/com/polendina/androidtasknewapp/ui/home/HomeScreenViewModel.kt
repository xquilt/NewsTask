package com.polendina.androidtasknewapp.ui.home

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.polendina.androidtasknewapp.domain.model.Publication
import com.polendina.androidtasknewapp.domain.repository.FakeNewsRepository
import com.polendina.androidtasknewapp.domain.repository.NewsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

interface HomeScreenViewModel {
    val newsFeed: SnapshotStateList<Publication>
}

class HomeScreenViewModelImpl(
    private val newsRepository: NewsRepository,
    val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO
): HomeScreenViewModel {
    private val scope = CoroutineScope(coroutineDispatcher)
    private val _newsFeed: SnapshotStateList<Publication> = mutableStateListOf()
    override val newsFeed = _newsFeed
    init {
        scope.launch {
            _newsFeed.addAll(
                scope.async { newsRepository.getTopHeadlines(searchQuery = "Tesla").body()?.articles ?: emptyList() }.await()
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
    init {
        _newsFeed.addAll(FakeNewsRepository().publications)
    }
}