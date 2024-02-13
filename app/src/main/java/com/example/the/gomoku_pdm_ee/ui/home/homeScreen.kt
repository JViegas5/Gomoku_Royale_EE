package com.example.the.gomoku_pdm_ee.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.the.gomoku_pdm_ee.ui.components.NavigationHandlers
import com.example.the.gomoku_pdm_ee.ui.components.TopBar
import com.example.the.gomoku_pdm_ee.ui.theme.GomokuRoyalePDMThemeEE

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onInfoRequested: () -> Unit = { }, navToGame: MutableState<Boolean>, onFavouriteGamesRequested: () -> Unit = {}, onDismiss: () -> Unit = {} , player1: MutableState<String>, player2: MutableState<String>) {
    val chooseName = remember { mutableStateOf(false) }
    GomokuRoyalePDMThemeEE {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Scaffold(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center),
                topBar = {
                    TopBar(
                        navigation = NavigationHandlers(
                            onFavouriteGamesRequested = onFavouriteGamesRequested
                        )
                    )
                }
            ) {
                Column(
                    modifier = Modifier
                        .padding(it)
                        .fillMaxSize()
                        .align(Alignment.Center),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(onClick = {
                        chooseName.value = true
                        print(chooseName.value)
                        println("Click")
                    }) {
                        Text(text = "PLAY")
                    }
                    if(chooseName.value) {
                        println("Hello")
                        ChoosePlayer(
                            player1 = player1,
                            player2 = player2,
                            choosePlayer = chooseName,
                            navToGame = navToGame
                        )
                    }
                }
            }
        }
    }
}