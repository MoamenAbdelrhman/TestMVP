package com.example.testmvp.view.Categories

import com.example.testmvp.model.FavoriteMeal

interface CategoriesView {
    fun showCategories(categories: List<FavoriteMeal>)
    fun showMessage(message: String)
}