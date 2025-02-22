package com.example.testmvp.Presenter

import android.widget.Toast
import com.example.testmvp.model.FavoriteMeal
import com.example.testmvp.model.db.FavoriteMealDao
import com.example.testmvp.model.server.RetrofitClient
import com.example.testmvp.view.Categories.CategoriesView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CategoriesPresenter(private val view: CategoriesView, private val dao: FavoriteMealDao) {

    suspend fun getData() {
        try {
            val response = RetrofitClient.favServiceObj.getCategories()
            val categories = response.body()?.categories
            withContext(Dispatchers.Main) {
                if (categories.isNullOrEmpty()) {
                    view.showMessage("No categories found")
                } else {
                    view.showCategories(categories)
                    view.showMessage("Recieved ${(categories).size}")
                }
            }
        } catch (e: Throwable) {
            e.printStackTrace()
            withContext(Dispatchers.Main) {
                view.showMessage("Error fetching categories")
            }
        }
    }

    suspend fun addMovie(meal: FavoriteMeal){
        val result = dao.addCategory(meal)
        withContext(Dispatchers.Main){
            if(result>0){
                view.showMessage("Added to Favorites ${meal.strCategory}")
            }else{
                view.showMessage("${meal.strCategory} is already in Favorites")
            }
        }
    }
}