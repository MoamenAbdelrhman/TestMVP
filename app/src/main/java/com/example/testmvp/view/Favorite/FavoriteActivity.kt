package com.example.testmvp.view.Favorite

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testmvp.Presenter.FavoritePresenter
import com.example.testmvp.model.MealListener
import com.example.testmvp.model.db.FavoriteDatabase
import com.example.testmvp.model.FavoriteMeal
import com.example.testmvp.model.db.FavoriteMealDao
import com.example.testmvp.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteActivity : AppCompatActivity(), FavoriteView, MealListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: FavoriteAdapter
    private lateinit var mealDao: FavoriteMealDao
    private lateinit var presenter: FavoritePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_favorite)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerView = findViewById(R.id.fav_rv_movies)
        progressBar = findViewById(R.id.fav_progress_bar_loading)
        adapter = FavoriteAdapter(listOf(), this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        setupPresenter()



        lifecycleScope.launch (Dispatchers.IO){
            presenter.loadFavorites()
        }
    }
    private fun setupPresenter(){
        mealDao = FavoriteDatabase.getDatabase(this).favoriteMealDao()
        presenter= FavoritePresenter(this, mealDao)

    }

    override fun onMealClick(meal: FavoriteMeal) {
        lifecycleScope.launch(Dispatchers.IO) {
            presenter.removeMeal(meal)
        }
    }

    override fun showFavorites(meals: List<FavoriteMeal>) {
        progressBar.visibility = View.GONE
        adapter.mealList = meals
        adapter.notifyDataSetChanged()
    }

    override fun showMessage(message: String) {
        progressBar.visibility = View.GONE
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
