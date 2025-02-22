package com.example.testmvp.model.db

import androidx.room.*
import com.example.testmvp.model.FavoriteMeal

@Dao
interface FavoriteMealDao {
    @Query("SELECT * FROM favorite_table")
    suspend fun getAllFavorites(): List<FavoriteMeal>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCategory(meal: FavoriteMeal): Long

    @Delete
    suspend fun removeCategory(meal: FavoriteMeal):Int
}
