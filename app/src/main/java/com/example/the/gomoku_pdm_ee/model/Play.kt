package com.example.the.gomoku_pdm_ee.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Play(val position: Int, val player: Player) : Parcelable