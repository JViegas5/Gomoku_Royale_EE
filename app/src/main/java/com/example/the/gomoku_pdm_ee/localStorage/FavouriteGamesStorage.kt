package com.example.the.gomoku_pdm_ee.localStorage

import android.content.Context
import android.content.SharedPreferences
import com.example.the.gomoku_pdm_ee.model.FavouriteGame
import com.google.gson.Gson


class FavouriteGamesStorage(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)

    private val gson = Gson()

    fun saveFavoriteGames(favoriteGames: List<FavouriteGame>) {
        val json = gson.toJson(favoriteGames)
        sharedPreferences.edit().putString("favoriteGames", json).apply()
    }

    fun getFavoriteGames(): List<FavouriteGame> {
        val json = sharedPreferences.getString("favoriteGames", "")
        return if (json.isNullOrBlank()) {
            emptyList()
        } else {
            gson.fromJson(json, Array<FavouriteGame>::class.java).toList()
        }
    }
}