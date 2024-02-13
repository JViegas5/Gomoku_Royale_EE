package com.example.the.gomoku_pdm_ee

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.the.gomoku_pdm_ee.model.Board
import com.example.the.gomoku_pdm_ee.model.Play
import com.example.the.gomoku_pdm_ee.model.Player
import com.example.the.gomoku_pdm_ee.ui.game.WinDialog
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

const val BOARD_DIM = 13

@Composable
fun Intersection(clicked: Boolean, replay: Boolean = true, player: Player, onClick: () -> Unit) {
    Box(modifier = Modifier
        .size(Dp(25.0f))
        .background(Color.Yellow)
        .clickable(!clicked && !replay) { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Divider(color = Color.Black, thickness = Dp(2.0f))
        Divider(color = Color.Black, thickness = Dp(2.0f), modifier = Modifier.rotate(90.0f))
        if (clicked) {
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .background(if (player == Player.A) Color.Black else Color.White)
            )
        }
    }
}

@SuppressLint("MutableCollectionMutableState")
@Composable
fun GridView(board: Board, player1: String?, player2: String?, name: MutableState<String> = mutableStateOf(""), replay: Boolean = false, plays: List<Play> = emptyList(), onDismiss: () -> Unit = {}, onSaveFavourite: () -> Unit = {}) {
    val turn = rememberSaveable{ mutableStateOf(Player.A) }
    val pieces = rememberSaveable { mutableStateOf(board) }
    val dialog = rememberSaveable { mutableStateOf(false) }
    val currTime = rememberSaveable { mutableStateOf(30) }

    val clickedList = rememberSaveable { mutableStateOf(MutableList(board.size * board.size) { false }) }

    val timerJob = remember { mutableStateOf<Job?>(null) }
    val won = rememberSaveable{ mutableStateOf<String?>("") }
    val scope = rememberCoroutineScope()

    if (dialog.value) {
        if(turn.value == Player.A) won.value=player2 else won.value=player1
        WinDialog(text = name,won.value ,onDismiss = onDismiss, onSaveFavourite = onSaveFavourite)
    }
    else {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "${player1} (White) vs ${player2} (Black)")
            if(!replay) Text("Player ${turn.value.name}'s turn (${if(turn.value == Player.A) "${player1} (White)" else "${player2} (Black)"})", )
            Text("You have ${currTime.value} seconds to play")
            Box(
                modifier = if(!replay) Modifier.fillMaxSize() else Modifier.align(Alignment.CenterHorizontally),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.border(BorderStroke(Dp(2.0f), Color.Black))
                ) {
                    Row {
                        repeat(board.size) { i ->
                            Column {
                                repeat(board.size) { j ->
                                    val clicked = rememberSaveable { mutableStateOf(false) }
                                    Intersection(
                                        clicked = if (!replay) clicked.value else clickedList.value[i + j * board.size],
                                        replay = false,
                                        player = pieces.value.get(j * board.size + i)
                                    ) {
                                        if (!replay) {
                                            clicked.value = true
                                            turn.value =
                                                if (turn.value == Player.A) Player.B else Player.A
                                            pieces.value.put(turn.value, i + j * board.size)
                                            if (board.checkWin(
                                                    i + j * board.size,
                                                    turn.value
                                                )
                                            ) dialog.value = true
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (replay) {
                Row {
                    val count = rememberSaveable { mutableStateOf(0) }
                    Button(onClick = {
                        pieces.value.remove(plays[count.value - 1].position)
                        clickedList.value[plays[count.value - 1].position] = false
                        count.value -= 1
                    }, enabled = count.value != 0) {
                        Text("Previous")
                    }
                    Button(onClick = {
                        pieces.value.put(plays[count.value].player, plays[count.value].position)
                        clickedList.value[plays[count.value].position] = true
                        count.value += 1
                    }, enabled = count.value != plays.size) {
                        Text("Next")
                    }
                }
            }
        }
    }

    DisposableEffect(turn.value) {
        fun startTimer() {
            timerJob.value?.cancel()
            currTime.value = 30
            timerJob.value = scope.launch {
                while (currTime.value > 0) {
                    delay(1000)
                    currTime.value--
                }
                println("TRUE TIMER")
                dialog.value = true
            }
        }
        startTimer()
        onDispose {
            timerJob.value?.cancel()
        }
    }


}



