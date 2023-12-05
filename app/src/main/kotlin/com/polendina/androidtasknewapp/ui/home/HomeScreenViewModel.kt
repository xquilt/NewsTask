package com.polendina.androidtasknewapp.ui.home

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.AndroidViewModel
import com.polendina.androidtasknewapp.data.database.model.PublicationEntity
import com.polendina.androidtasknewapp.data.database.model.PublicationsDb
import com.polendina.androidtasknewapp.di.NetworkConnectivity
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
    private val application: Application = Application(),
    val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO,
): HomeScreenViewModel, AndroidViewModel(application) {
    private val scope = CoroutineScope(coroutineDispatcher)
    private val _newsFeed: SnapshotStateList<Publication> = mutableStateListOf()
    override val newsFeed = _newsFeed
    private var _searchQuery = mutableStateOf("")
    override var searchQuery: MutableState<String> = _searchQuery
    private val database by lazy {
        PublicationsDb.getDatabase(context = application.applicationContext)
    }
    private val networkConnectivity: NetworkConnectivity
    override val categories: List<Constants.Categories>
        get() = Constants.Categories.entries
    override fun searchArticles(searchQuery: String): Job = scope.launch {
        _newsFeed.addAll(
    async { newsRepository.getNewsEverything(searchQuery = searchQuery) }
            .await()
            .also { if(it.isSuccessful) _newsFeed.clear() }
            .body()?.articles ?: emptyList<Publication>()
            .also {
                it.forEach {
                    database.applicationsDao().upsert(publication = PublicationEntity(
                        id = it.id,
                        title = it.title,
                        author = it.title
                    ))
                }
            }
        )
    }

    init {
        networkConnectivity = NetworkConnectivity(context = application.applicationContext)
        scope.launch {
            _newsFeed.addAll(
                scope.async {
                    if (networkConnectivity.isNetworkAvailable()) {
                        newsRepository.getTopHeadlines(searchQuery = "").body()?.articles ?: emptyList()
                    } else {
                        database.applicationsDao().getAllPublications().map {
                            with(it) {
                                Publication(
                                    id = id, title = title, author = author, description = null, url = null,
                                    urlToImage = null, publishedAt = null, content = null, source = null
                                )
                            }
                        }
                    }
                }.await()
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