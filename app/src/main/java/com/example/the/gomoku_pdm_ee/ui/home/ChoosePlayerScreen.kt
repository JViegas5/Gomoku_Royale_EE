package com.example.the.gomoku_pdm_ee.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChoosePlayer(player1: MutableState<String>, player2: MutableState<String>, choosePlayer: MutableState<Boolean>, navToGame: MutableState<Boolean>) {
    AlertDialog(
        onDismissRequest = { choosePlayer.value = false },
        title = { Text(text = "Choose your player names") },
        confirmButton = {
            Column {
                OutlinedTextField(
                    value = player1.value,
                    onValueChange = { player1.value = it },
                    label = { Text("Player 1 (White) : ") }
                )
                OutlinedTextField(
                    value = player2.value,
                    onValueChange = { player2.value = it },
                    label = { Text("Player 2 (Black) : ") }
                )
                Row {
                    Button(onClick = {
                        navToGame.value = true
                        choosePlayer.value = false
                    } ) {
                        Text(text = "Play")
                    }
                    Button(onClick = { choosePlayer.value = false }) {
                        Text(text = "Back")
                    }
                }
            }
        })
}