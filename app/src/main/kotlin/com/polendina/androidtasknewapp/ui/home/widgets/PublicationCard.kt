package com.polendina.androidtasknewapp.ui.home.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.polendina.androidtasknewapp.R
import com.polendina.androidtasknewapp.domain.model.Publication

@Composable
fun PublicationCard(
    publication: Publication,
    modifier: Modifier = Modifier
) {
    Card (
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onPrimary
        ),
        modifier = modifier
            .width(300.dp)
            .height(270.dp)
            .shadow(elevation = 10.dp, shape = RoundedCornerShape(10.dp), clip = true)
            .clip(RoundedCornerShape(10.dp))
    ) {
        // TODO: There should be a picture not found placeholder!
        Box(
            modifier = Modifier
                .height(180.dp)
        ) {
            AsyncImage(
                model = publication.urlToImage,
                contentDescription = null,
                contentScale = ContentScale.FillBounds
            )
        }
        Column (
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(
                    vertical = 10.dp,
                    horizontal = 10.dp,
                )
                .fillMaxHeight()
        ) {
            Text(
                text = publication.title ?: stringResource(id = R.string.no_title),
                // TODO: Move the styling code somewhere else to leverage code reuse!
                style = TextStyle(
                    fontWeight = FontWeight.SemiBold
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(bottom = 10.dp)
            )
            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row (
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.outline
                    )
                    Text(
                        text = publication.publishedAt ?: stringResource(id = R.string.no_date),
                        style = TextStyle(
                            color = MaterialTheme.colorScheme.outline
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Text(
                    text = publication.author ?: stringResource(id = R.string.no_author),
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.outline
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}