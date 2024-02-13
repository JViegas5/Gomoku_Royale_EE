package com.example.the.gomoku_pdm_ee.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.the.gomoku_pdm_ee.R
import com.example.the.gomoku_pdm_ee.ui.theme.GomokuRoyalePDMThemeEE

data class NavigationHandlers(
    val onBackRequested: (() -> Unit)? = null,
    val onInfoRequested: (() -> Unit)? = null,
    val onHomeRequested: (() -> Unit)? = null,
    val onGameRequested: (() -> Unit)? = null,
    val onFavouriteGamesRequested: (() -> Unit)? = null
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(navigation: NavigationHandlers = NavigationHandlers()) {
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.app_name)) },
        navigationIcon = {
            if (navigation.onBackRequested != null) {
                IconButton(onClick = navigation.onBackRequested) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = stringResource(
                        id = R.string.top_bar_go_back
                    )
                    )
                }
            }
        },
        actions = {
            if (navigation.onInfoRequested != null) {
                IconButton(
                    onClick = navigation.onInfoRequested
                ) {
                    Icon(
                        imageVector = Icons.Default.List,
                        contentDescription = stringResource(id = R.string.top_bar_navigate_to_about)
                    )
                }
            }

            if (navigation.onHomeRequested != null) {
                IconButton(
                    onClick = navigation.onHomeRequested
                ) {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = stringResource(id = R.string.top_bar_navigate_to_home)
                    )
                }
            }

            if (navigation.onGameRequested != null) {
                IconButton(
                    onClick = navigation.onGameRequested
                ) {
                    Icon(
                        imageVector = Icons.Default.Send,
                        contentDescription = stringResource(id = R.string.top_bar_navigate_to_game)
                    )
                }
            }

            if (navigation.onFavouriteGamesRequested != null) {
                IconButton(onClick = navigation.onFavouriteGamesRequested) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = stringResource(id = R.string.favourite_games)
                    )
                }
            }

        }
    )
}

@Preview
@Composable
private fun TopBarPreviewInfoAndHistory() {
    GomokuRoyalePDMThemeEE {
        TopBar(
            navigation = NavigationHandlers(onInfoRequested = { })
        )
    }
}

@Preview
@Composable
private fun TopBarPreviewBackAndInfo() {
    GomokuRoyalePDMThemeEE {
        TopBar(navigation = NavigationHandlers(onBackRequested = { }, onInfoRequested = { }))
    }
}

@Preview
@Composable
private fun TopBarPreviewBack() {
    GomokuRoyalePDMThemeEE {
        TopBar(navigation = NavigationHandlers(onBackRequested = { }))
    }
}