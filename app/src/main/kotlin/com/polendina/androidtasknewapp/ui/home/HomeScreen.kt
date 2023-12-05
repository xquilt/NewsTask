package com.polendina.androidtasknewapp.ui.home

import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.polendina.androidtasknewapp.ui.BottomBar
import com.polendina.androidtasknewapp.ui.home.widgets.HorizontalPublication
import com.polendina.androidtasknewapp.ui.home.widgets.PublicationCard
import com.polendina.androidtasknewapp.ui.home.widgets.TopBarSection
import com.polendina.androidtasknewapp.ui.theme.AndroidTaskNewAppTheme
import com.polendina.androidtasknewapp.utils.Constants
import kotlinx.coroutines.runBlocking

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeScreenViewModel: HomeScreenViewModel
) {
    Scaffold (
        topBar = {
            TopBarSection(
                modifier = modifier
                    .fillMaxWidth()
            ) {
                // TODO: Get dimensions in a separate dimensions resource file!
                Spacer(modifier = Modifier.padding(vertical = 10.dp))
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
        Column (
            modifier = Modifier
                .fillMaxSize()
                .fillMaxHeight()
                .padding(it)
        ) {
            SearchBar(
                query = "",
                onQueryChange = {},
                onSearch = {},
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
                        imageVector = Icons.Default.Menu,
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
                items(Constants.Categories.entries) {
                    Box (
                        modifier = Modifier
                            .clip(RoundedCornerShape(15.dp))
                            .background(MaterialTheme.colorScheme.primary)
                            .padding(10.dp)
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
            LazyRow (
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                items(homeScreenViewModel.newsFeed) {
                    PublicationCard(
                        publication = it,
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
                    )
                }
            }
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
            LazyColumn {
                items(homeScreenViewModel.newsFeed.reversed()) {
                    HorizontalPublication(
                        publication = it,
                        modifier = Modifier
                            .height(100.dp)
                    )
                    Divider()
                }
            }
        }
    }
}

@Preview(name = "Home screen (Light mode)", showBackground = true)
@Composable
fun HomeScreenPreview() {
    AndroidTaskNewAppTheme {
        HomeScreen(homeScreenViewModel = HomeScreenViewModelMock())
    }
}