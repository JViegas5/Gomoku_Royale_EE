package com.example.the.gomoku_pdm_ee.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Board(val size: Int, val turn: Player = Player.A) : Parcelable {
    private val plays = mutableListOf<Play>()

    private val list: MutableList<Player> = MutableList(size * size) { Player.NONE }
    fun put(player: Player, position: Int) {
        list[position] = player
        plays += Play(position, player)
    }

    fun remove(position: Int) {
        list[position] = Player.NONE
        plays.removeIf { it.position == position }
    }

    fun get(position: Int): Player = list[position]

    fun getPlays(): List<Play> = plays

    fun checkWin(position: Int, player: Player): Boolean {
        val row = position / size
        val col = position % size
        var openSpaceDown = false
        var openSpaceUpper = false

        // Check horizontal
        var count = 0
        for (i in 0 until size) {
            if (list[row * size + i] == player) {
                count++
                if (count == 5) return true
            } else {
                count = 0
            }
        }

        // Check vertical
        count = 0
        for (i in 0 until size) {
            if (list[i * size + col] == player) {
                count++
                if (count == 5) return true
            } else {
                count = 0
            }
        }
        count = -1
        for (i in 0 until size) {
            val rUp = row - i
            val cUp = col - i
            val rDown = row + i
            val cDown = col + i
            val checkBoardersUpwards = (rUp >= 0 && rUp < size && cUp >= 0 && cUp < size)
            val checkBoardersDown = (rDown >= 0 && rDown < size && cDown >= 0 && cDown < size)
            if (checkBoardersUpwards){
                if (list[rUp * size + cUp] == player && !openSpaceUpper) {
                    count++
                }
                else {
                    openSpaceUpper = true
                }
            }

            if (checkBoardersDown){
                if (list[rDown * size + cDown] == player && !openSpaceDown) {
                    count++
                }
                else {
                    openSpaceDown = true
                }
            }

            println("COUNT : "+count)

            if (count == 5) return true
            if (openSpaceUpper && openSpaceDown)
                count = -1
        }

        // Check diagonal (top-left to bottom-right)
        openSpaceUpper = false
        openSpaceDown = false
        count = -1
        for (i in 0 until size) {
            val rUp = row + i
            val cUp = col - i
            val rDown = row - i
            val cDown = col + i
            val checkBoardersUpwards = (rUp >= 0 && rUp < size && cUp >= 0 && cUp < size)
            val checkBoardersDown = (rDown >= 0 && rDown < size && cDown >= 0 && cDown < size)
            if (checkBoardersUpwards){
                if (list[rUp * size + cUp] == player && !openSpaceUpper) {
                    count++
                }
                else {
                    openSpaceUpper = true
                }
            }

            if (checkBoardersDown){
                if (list[rDown * size + cDown] == player && !openSpaceDown) {
                    count++
                }
                else {
                    openSpaceDown = true
                }
            }

            println("COUNT : "+count)

            if (count == 5) return true
            if (openSpaceUpper && openSpaceDown)
                count = -1
        }

        return false
    }

}