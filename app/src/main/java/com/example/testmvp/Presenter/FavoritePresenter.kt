package com.example.testmvp.Presenter

import androidx.lifecycle.lifecycleScope
import com.example.testmvp.model.FavoriteMeal
import com.example.testmvp.model.db.FavoriteMealDao
import com.example.testmvp.view.Favorite.FavoriteView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoritePresenter(private val view: FavoriteView, private val dao: FavoriteMealDao) {

    suspend fun loadFavorites() {
            val meals = dao.getAllFavorites()
            withContext(Dispatchers.Main) {
                if(meals.isEmpty()){
                    view.showMessage("Couldn't get any Movies")
                }else{
                    view.showFavorites(meals)
                }
            }
    }

    suspend fun removeMeal(meal: FavoriteMeal) {
        val result = dao.removeCategory(meal)
        withContext(Dispatchers.Main) {
            if (result > 0) {
                view.showMessage("Removed ${meal.strCategory}")

            } else {
                view.showMessage("${meal.strCategory} is already removed")
            }
        }
        loadFavorites()  // Refresh the list
    }
}