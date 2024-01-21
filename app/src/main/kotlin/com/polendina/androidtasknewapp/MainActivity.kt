package com.polendina.androidtasknewapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import com.polendina.androidtasknewapp.data.repository.NewsRepositoryImpl
import com.polendina.androidtasknewapp.ui.NavGraphs
import com.polendina.androidtasknewapp.ui.destinations.HomeScreenDestination
import com.polendina.androidtasknewapp.ui.home.HomeScreen
import com.polendina.androidtasknewapp.ui.home.HomeScreenViewModelImpl
import com.polendina.androidtasknewapp.ui.theme.AndroidTaskNewAppTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.manualcomposablecalls.composable

class MainActivity : ComponentActivity() {
    private lateinit var appBroadcastReceiver: AppBroadcastReceiver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidTaskNewAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var airplaneModeVisible by remember { mutableStateOf(false) }
                    appBroadcastReceiver = AppBroadcastReceiver(
                        airplaneModeCallback = {
                            Toast.makeText(this, "AirPlane mode changed!", Toast.LENGTH_SHORT).show()
                            airplaneModeVisible = !airplaneModeVisible
                        }
                    )
                    ContextCompat.registerReceiver(
                        application,
                        appBroadcastReceiver,
                        IntentFilter().apply {
                            addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
                            addAction(Intent.ACTION_PASTE)
                        },
                        ContextCompat.RECEIVER_EXPORTED
                    )
                    DestinationsNavHost(navGraph = NavGraphs.root) {
                        composable(HomeScreenDestination) {
                            HomeScreen(
                                navigator = destinationsNavigator,
                                homeScreenViewModel = HomeScreenViewModelImpl(
                                    newsRepository = NewsRepositoryImpl(),
                                    application = application,
                                ),
                                airplaneModeVisible = airplaneModeVisible
                            )
                        }
                    }
                }
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(appBroadcastReceiver)
    }
}
class AppBroadcastReceiver(
    private val airplaneModeCallback: (Boolean) -> Unit,
): BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        when(intent?.action) {
            Intent.ACTION_AIRPLANE_MODE_CHANGED -> {
                airplaneModeCallback(intent.getBooleanExtra("state", false))
            }
            Intent.ACTION_PASTE -> {
//                Toast.makeText(context, "Text pasted", Toast.LENGTH_SHORT).show()
            }
        }
    }
}