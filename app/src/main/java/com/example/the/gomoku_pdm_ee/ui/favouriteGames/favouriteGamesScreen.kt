package com.example.the.gomoku_pdm_ee.ui.favouriteGames

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import com.example.the.gomoku_pdm_ee.NavigationHandlers
import com.example.the.gomoku_pdm_ee.TopBar
import com.example.the.gomoku_pdm_ee.model.FavouriteGame
import com.example.the.gomoku_pdm_ee.ui.theme.GomokuRoyalePDMThemeEE
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouritesScreen(onBackRequested: () -> Unit = { }, onHomeRequested: () -> Unit = {}, onGamePressed: (game: FavouriteGame) -> Unit = {}, context: Context) {
    val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
    val favourites = sharedPreferences.getString("favoriteGames", "DefaultGame")

    val gson = Gson()

    val type = object : TypeToken<List<FavouriteGame>>() {}.type
    val favouriteGames: List<FavouriteGame> = gson.fromJson(favourites, type)

    GomokuRoyalePDMThemeEE {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = { TopBar(navigation = NavigationHandlers(onHomeRequested = onHomeRequested, onBackRequested = onBackRequested)) }
        ) {
            Column(Modifier.padding(it)) {
                for (i in favouriteGames) {
                    Row {
                        Button(onClick = { onGamePressed(i) }) {
                            Text(text = i.name)
                        }
                    }
                }
            }
        }
    }
}