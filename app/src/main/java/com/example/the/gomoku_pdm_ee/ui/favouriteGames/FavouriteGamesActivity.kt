package com.example.the.gomoku_pdm_ee.ui.favouriteGames

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import com.example.the.gomoku_pdm_ee.ui.BaseActivity
import com.example.the.gomoku_pdm_ee.ui.gameReplay.GameReplayActivity
import com.example.the.gomoku_pdm_ee.ui.home.HomeActivity

class FavouriteGamesActivity: BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FavouritesScreen(
                onHomeRequested = { HomeActivity.navigateTo(this) },
                onBackRequested = { finish() },
                onGamePressed = { game ->
                    navigate<GameReplayActivity> {
                        it.putExtra(GameReplayActivity.GameInfoParamName, game.name)
                    }
                },
                context = this
            )
        }
    }

    companion object {
        fun navigateTo(origin: Activity) {
            val intent = Intent(origin, FavouriteGamesActivity::class.java)
            origin.startActivity(intent)
        }
    }
}