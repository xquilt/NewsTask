package com.polendina.androidtasknewapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.polendina.androidtasknewapp.data.repository.NewsRepositoryImpl
import com.polendina.androidtasknewapp.ui.NavGraphs
import com.polendina.androidtasknewapp.ui.destinations.HomeScreenDestination
import com.polendina.androidtasknewapp.ui.home.HomeScreen
import com.polendina.androidtasknewapp.ui.home.HomeScreenViewModelImpl
import com.polendina.androidtasknewapp.ui.theme.AndroidTaskNewAppTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.manualcomposablecalls.composable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidTaskNewAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DestinationsNavHost(navGraph = NavGraphs.root) {
                        composable(HomeScreenDestination) {
                            HomeScreen(
                                navigator = destinationsNavigator,
                                homeScreenViewModel = HomeScreenViewModelImpl(
                                    newsRepository = NewsRepositoryImpl(),
                                    application = application
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}