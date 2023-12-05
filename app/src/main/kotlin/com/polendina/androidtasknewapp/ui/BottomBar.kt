package com.polendina.androidtasknewapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.polendina.androidtasknewapp.R

enum class BottomBarDestinations(val title: Int, val icon: ImageVector) {
    HOME(title = R.string.home, icon = Icons.Outlined.Home),
    SEARCH(title = R.string.search, icon = Icons.Default.Search),
    FAVORITE(title = R.string.favorites, icon = Icons.Default.FavoriteBorder),
    PROFILE(title = R.string.profile, icon = Icons.Outlined.AccountCircle),
}

@Composable
fun BottomBar() {
    Row (
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .height(60.dp)
            .padding(10.dp)
    ) {
        BottomBarDestinations.entries.forEach {
            FilledTonalIconButton(
                onClick = { /*TODO*/ },
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = if (it.ordinal == 0) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onPrimary
                ),
            ) {
                Icon(
                    imageVector = it.icon,
                    contentDescription = null,
                    tint = if (it.ordinal == 0) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.outline
                )
            }
        }
    }
}