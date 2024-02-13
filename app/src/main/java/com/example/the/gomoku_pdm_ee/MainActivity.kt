package com.example.the.gomoku_pdm_ee

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.the.gomoku_pdm_ee.ui.favouriteGames.FavouriteGamesActivity
import com.example.the.gomoku_pdm_ee.ui.game.GameActivity
import com.example.the.gomoku_pdm_ee.ui.home.HomeActivity
import com.example.the.gomoku_pdm_ee.ui.home.HomeScreen
import com.example.the.gomoku_pdm_ee.ui.theme.GomokuRoyalePDMThemeEE

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        HomeActivity.navigateTo(this)
    }
}