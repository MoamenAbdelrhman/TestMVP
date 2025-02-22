package com.example.testmvp.view.Favorite

import com.example.testmvp.model.FavoriteMeal

interface FavoriteView {
    fun showFavorites(meals: List<FavoriteMeal>)
    fun showMessage(message: String)
}