package com.example.the.gomoku_pdm_ee.ui.game

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
fun WinDialog(text: MutableState<String>, won: String?, onDismiss: () -> Unit, onSaveFavourite: () -> Unit) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text(text = "Congratulations!") },
        text = { Text(text = "$won has won the game!") },
        confirmButton = {
            Column {
                Text("Add to favourites?")
                OutlinedTextField(
                    value = text.value,
                    onValueChange = { text.value = it },
                    label = { Text("Game name") }
                )
                Row {
                    Button(onClick = { onSaveFavourite() }) {
                        Text(text = "Yes")
                    }
                    Button(onClick = { onDismiss() }) {
                        Text(text = "No")
                    }
                }
            }
        })
}