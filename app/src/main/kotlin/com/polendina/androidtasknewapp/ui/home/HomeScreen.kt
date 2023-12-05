package com.polendina.androidtasknewapp.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.polendina.androidtasknewapp.R
import com.polendina.androidtasknewapp.data.repository.NewsRepositoryImpl
import com.polendina.androidtasknewapp.ui.BottomBar
import com.polendina.androidtasknewapp.ui.destinations.WebViewScreenDestination
import com.polendina.androidtasknewapp.ui.home.widgets.HorizontalPublication
import com.polendina.androidtasknewapp.ui.home.widgets.PublicationCard
import com.polendina.androidtasknewapp.ui.home.widgets.TopBarSection
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator

@RootNavGraph(start = true)
@Destination
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeScreenViewModel: HomeScreenViewModel = HomeScreenViewModelImpl(newsRepository = NewsRepositoryImpl()),
    navigator: DestinationsNavigator
) {
    Scaffold (
        topBar = {
            Column {
                TopBarSection(
                    modifier = modifier
                        .fillMaxWidth()
                ) {
                    // TODO: Get dimensions in a separate dimensions resource file!
                    Spacer(
                        modifier = Modifier.padding(vertical = 10.dp)
                    )
                }
                SearchBar(
                    query = homeScreenViewModel.searchQuery.value,
                    onQueryChange = {
                        homeScreenViewModel.searchQuery.value = it
                    },
                    onSearch = {
                        homeScreenViewModel.searchArticles(searchQuery = it)
                    },
                    active = true,
                    onActiveChange = {},
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null
                        )
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.Dashboard,
                            contentDescription = null
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp)
                        .clip(RoundedCornerShape(25.dp))
                ) { }
                LazyRow (
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                    modifier = Modifier
                        .padding(vertical = 15.dp)
                ) {
                    items(homeScreenViewModel.categories) {
                        Box (
                            modifier = Modifier
                                .clip(RoundedCornerShape(15.dp))
                                .background(MaterialTheme.colorScheme.primary)
                                .padding(10.dp)
                                .clickable {
                                    homeScreenViewModel.searchArticles(
                                        searchQuery = it.title
                                    )
                                }
                        ) {
                            Text(
                                text = it.title,
                                style = TextStyle(
                                    color = MaterialTheme.colorScheme.onPrimary
                                )
                            )
                        }
                    }
                }
            }
        },
        bottomBar = { BottomBar() },
        modifier = Modifier
            .padding(
                top = 10.dp,
                start = 10.dp,
                end = 10.dp
            )
    ) {
        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
                .fillMaxHeight()
                .padding(it)
        ) {
            item {
                LazyRow (
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                ) {
                    items(homeScreenViewModel.newsFeed) {
                        PublicationCard(
                            publication = it,
                            modifier = Modifier
                                .padding(horizontal = 5.dp)
                        )
                    }
                }
            }
            item {
                Text(
                    text = stringResource(id = R.string.latest_news),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp
                    ),
                    modifier = Modifier
                        .padding(
                            top = 15.dp,
                            bottom = 10.dp
                        )
                )
            }
            items(homeScreenViewModel.newsFeed.reversed()) {
                HorizontalPublication(
                    publication = it,
                    modifier = Modifier
                        .height(100.dp)
                        .padding(vertical = 10.dp)
                        .clickable {
                            navigator.navigate(
                                WebViewScreenDestination(publicationUrl = it.url ?: "")
                            )
                        }
                )
                Divider()
            }
        }
    }
}

@Preview(name = "Home screen (Light mode)", showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        homeScreenViewModel = HomeScreenViewModelMock(),
        navigator = EmptyDestinationsNavigator
    )
}