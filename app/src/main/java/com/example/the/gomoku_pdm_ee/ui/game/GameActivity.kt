package com.example.the.gomoku_pdm_ee.ui.game

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import com.example.the.gomoku_pdm_ee.BOARD_DIM
import com.example.the.gomoku_pdm_ee.localStorage.FavouriteGamesStorage
import com.example.the.gomoku_pdm_ee.model.Board
import com.example.the.gomoku_pdm_ee.model.FavouriteGame
import com.example.the.gomoku_pdm_ee.model.Play
import com.example.the.gomoku_pdm_ee.ui.BaseActivity
import com.example.the.gomoku_pdm_ee.ui.home.HomeActivity

class GameActivity : BaseActivity() {

    companion object {
        fun navigateTo(origin: Activity, player1: MutableState<String>, player2: MutableState<String>) {
            val intent = Intent(origin, GameActivity::class.java)
            println("Navigate : "+player1.value)
            intent.putExtra("Player1", player1.value)
            intent.putExtra("Player2", player2.value)
            origin.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val favouriteGames = FavouriteGamesStorage(this)

        setContent {
            val favouriteGameList = remember {
                mutableStateOf(favouriteGames.getFavoriteGames())
            }

            val name = remember { mutableStateOf("") }
            val player1 = rememberSaveable { mutableStateOf(intent.extras?.getString("Player1")) }
            val player2 = rememberSaveable { mutableStateOf(intent.extras?.getString("Player2")) }
            //val plays = rememberSaveable { mutableListOf<Play>() }
            val board = rememberSaveable{ mutableStateOf(Board(BOARD_DIM)) }

            GameScreen(
                board.value,
                player1.value,
                player2.value,
                name = name,
                onInfoRequested = {},
                onHomeRequested = { HomeActivity.navigateTo(this) },
                onDismiss = { finish() },
                onSaveFavourite = {
                    val game = FavouriteGame(name.value, player1.value, player2.value, board.value.getPlays())
                    favouriteGames.saveFavoriteGames(favouriteGameList.value + game)
                    favouriteGameList.value += game
                    HomeActivity.navigateTo(this)
                })
        }

    }

}