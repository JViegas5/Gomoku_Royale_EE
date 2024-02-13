package com.example.the.gomoku_pdm_ee.ui.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import com.example.the.gomoku_pdm_ee.ui.BaseActivity
import com.example.the.gomoku_pdm_ee.ui.favouriteGames.FavouriteGamesActivity
import com.example.the.gomoku_pdm_ee.ui.game.GameActivity

class HomeActivity : BaseActivity() {
    companion object {
        fun navigateTo(origin: Activity) {
            val intent = Intent(origin, HomeActivity::class.java)
            origin.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navToGame = rememberSaveable { mutableStateOf(false) }
            val player1 = rememberSaveable { mutableStateOf("") }
            val player2 = rememberSaveable { mutableStateOf("") }
            HomeScreen(navToGame = navToGame, onFavouriteGamesRequested = { FavouriteGamesActivity.navigateTo(this) }, player1 = player1, player2 = player2)
            if (navToGame.value){
                navToGame.value = false
                GameActivity.navigateTo(this, player1, player2)
            }
        }

    }
}