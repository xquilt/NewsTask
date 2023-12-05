package com.polendina.androidtasknewapp.ui.webview.widgets

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WebViewTopBar(
    modifier: Modifier = Modifier,
    navigationBack: () -> Unit,
    bookmarkArticle: () -> Unit,
    moreOptions:() -> Unit
) {
    TopAppBar (
        title = {
        },
        navigationIcon = {
            // TODO: Add arrow back navigation
            IconButton(
                onClick = navigationBack
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBackIos,
                    contentDescription = null
                )
            }
        },
        actions = {
            IconButton(onClick = bookmarkArticle ) {
                Icon(
                    imageVector = Icons.Default.BookmarkBorder,
                    contentDescription = null
                )
            }
            IconButton(onClick = moreOptions) {
                Icon(
                    imageVector = Icons.Default.MoreHoriz,
                    contentDescription = null
                )
            }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            actionIconContentColor = MaterialTheme.colorScheme.primary
        ),
        modifier = Modifier
    )
}