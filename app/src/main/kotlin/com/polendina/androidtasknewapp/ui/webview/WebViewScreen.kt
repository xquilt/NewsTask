package com.polendina.androidtasknewapp.ui.search

import android.content.Intent
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.polendina.androidtasknewapp.domain.model.Publication
import com.polendina.androidtasknewapp.domain.repository.FakeNewsRepository
import com.polendina.androidtasknewapp.ui.destinations.HomeScreenDestination
import com.polendina.androidtasknewapp.ui.theme.AndroidTaskNewAppTheme
import com.polendina.androidtasknewapp.ui.webview.widgets.WebViewTopBar
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator

@Destination
@Composable
fun WebViewScreen(
    modifier: Modifier = Modifier,
    publicationUrl: String,
    navigator: DestinationsNavigator
) {
    val context = LocalContext.current
    Scaffold (
        topBar = {
            WebViewTopBar(
                navigationBack = {
                    navigator.navigate(HomeScreenDestination())
                },
                bookmarkArticle = { /*TODO*/ },
                moreOptions = { /*TODO*/ }
            )
        },
        floatingActionButton = {
            IconButton(
                onClick = {
                    context.startActivity(Intent.createChooser(
                        Intent().apply {
                            action = Intent.ACTION_SEND
                            putExtra(Intent.EXTRA_INTENT, publicationUrl)
                            type = "text/plain"
                        },
                        null
                    ))
                },
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                ),
                modifier = Modifier
                    .size(60.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    ) {
        AndroidView(
            factory = {
                WebView(it).apply {
                    settings.javaScriptEnabled = true
                    webViewClient = WebViewClient()
                    // TODO: Doa  conditional null check to display other screen in the absence of a URL
                    loadUrl(publicationUrl)
                }
            },
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        )
    }
}

@Preview(name = "Article webview - Light mode", showBackground = true)
@Composable
fun PublicationView() {
    AndroidTaskNewAppTheme {
        WebViewScreen(
            publicationUrl = FakeNewsRepository().publications.random().url ?: "",
            navigator = EmptyDestinationsNavigator
        )
    }
}