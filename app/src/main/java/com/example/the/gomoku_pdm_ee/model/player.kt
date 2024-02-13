package com.example.the.gomoku_pdm_ee.model

enum class Player {
    A, B, NONE
}

fun other(player: Player): Player {
    return if (player == Player.A) Player.B
    else Player.A
}