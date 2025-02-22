package com.example.testmvp.view.Categories

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
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.example.testmvp.Presenter.CategoriesPresenter
import com.example.testmvp.model.MealListener
import com.example.testmvp.model.db.FavoriteDatabase
import com.example.testmvp.model.FavoriteMeal
import com.example.testmvp.model.db.FavoriteMealDao
import com.example.testmvp.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoriesActivity : AppCompatActivity() , CategoriesView, MealListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CategoryAdapter
    lateinit var progressBar : ProgressBar
    lateinit var dao : FavoriteMealDao
    private lateinit var presenter: CategoriesPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_all_categories)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerView = findViewById(R.id.rv_meals)
        progressBar = findViewById(R.id.progress_bar_loading)
        adapter = CategoryAdapter(listOf(), this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this, VERTICAL, false)

        dao = FavoriteDatabase.getDatabase(this).favoriteMealDao()
        presenter = CategoriesPresenter(this, dao)

        lifecycleScope.launch (Dispatchers.IO){
            presenter.getData()
        }

    }

    override fun onMealClick(meal: FavoriteMeal) {
        lifecycleScope.launch (Dispatchers.IO){
            presenter.addMovie(meal)
        }
    }

    override fun showCategories(categories: List<FavoriteMeal>) {
        progressBar.visibility = View.GONE
        adapter.mealList = categories
        adapter.notifyDataSetChanged()
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}