package com.example.the.gomoku_pdm_ee.ui.gameReplay

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.the.gomoku_pdm_ee.ui.components.GridView
import com.example.the.gomoku_pdm_ee.ui.components.NavigationHandlers
import com.example.the.gomoku_pdm_ee.ui.components.TopBar
import com.example.the.gomoku_pdm_ee.model.Board
import com.example.the.gomoku_pdm_ee.model.FavouriteGame
import com.example.the.gomoku_pdm_ee.ui.theme.GomokuRoyalePDMThemeEE
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameReplayScreen(context: Context, name: String, board: Board, onBackRequested: () -> Unit = { }) {
    val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
    val favourites = sharedPreferences.getString("favoriteGames", "DefaultGame")

    val gson = Gson()

    val type = object : TypeToken<List<FavouriteGame>>() {}.type
    val favouriteGames: List<FavouriteGame> = gson.fromJson(favourites, type)
    val favouriteGame = favouriteGames.find { it.name == name } ?: throw Resources.NotFoundException()

    val boardReplay = remember { mutableStateOf(board) }
    GomokuRoyalePDMThemeEE {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = { TopBar(navigation = NavigationHandlers(onBackRequested = onBackRequested)) }
        ) {
            Column(modifier = Modifier
                .padding(it)
                .fillMaxSize()) {
                GridView(board = boardReplay.value, favouriteGame.player1, favouriteGame.player2, replay = true, plays = favouriteGame.plays)
            }
        }
    }
}