package com.example.the.gomoku_pdm_ee.ui.game

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import com.example.the.gomoku_pdm_ee.ui.components.GridView
import com.example.the.gomoku_pdm_ee.ui.components.NavigationHandlers
import com.example.the.gomoku_pdm_ee.ui.components.TopBar
import com.example.the.gomoku_pdm_ee.model.Board
import com.example.the.gomoku_pdm_ee.ui.theme.GomokuRoyalePDMThemeEE

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScreen(board: Board, player1: String?, player2: String?, name: MutableState<String> = mutableStateOf(""), onInfoRequested: () -> Unit = { }, onHomeRequested:() -> Unit = { }, onDismiss: () -> Unit = { }, onSaveFavourite: () -> Unit = { }) {
    GomokuRoyalePDMThemeEE {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = { TopBar(navigation = NavigationHandlers(onInfoRequested = onInfoRequested, onHomeRequested = onHomeRequested)) }
        ) {
            Column(modifier = Modifier
                .padding(it)
                .fillMaxSize()) {
                GridView(board, player1, player2, name, onDismiss = onDismiss, onSaveFavourite = onSaveFavourite)
            }
        }
    }
}