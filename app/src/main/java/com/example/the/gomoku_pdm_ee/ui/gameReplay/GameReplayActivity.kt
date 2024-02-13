package com.example.the.gomoku_pdm_ee.ui.gameReplay

import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import com.example.the.gomoku_pdm_ee.model.Board
import com.example.the.gomoku_pdm_ee.model.Play
import com.example.the.gomoku_pdm_ee.ui.BaseActivity

class GameReplayActivity : BaseActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val game = intent.getStringExtra(GameInfoParamName)

        if (game == null) {
            finish()
            return
        }

        setContent {
            val board = Board(13)

            GameReplayScreen(
                context = this,
                name = game,
                board = board,
                onBackRequested = { finish() }
            )
        }
    }

    companion object {
        const val GameInfoParamName = "MY_INTENT_EXTRA"
    }
}