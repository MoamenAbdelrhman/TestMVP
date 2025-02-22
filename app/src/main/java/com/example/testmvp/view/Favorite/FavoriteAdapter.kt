package com.example.testmvp.view.Favorite

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testmvp.R
import com.example.testmvp.model.MealListener
import com.example.testmvp.model.FavoriteMeal


class FavoriteAdapter(
    var mealList: List<FavoriteMeal>,
    var movieListener: MealListener,
) : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    private lateinit var context: Context

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val mealName: TextView = view.findViewById(R.id.tv_CategoryName)
        val mealImage: ImageView = view.findViewById(R.id.iv_category)
        val btnDelete: Button = view.findViewById(R.id.btnDeleteFavorite)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_fav, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val meal: FavoriteMeal = mealList[position]
        holder.mealName.text = meal.strCategory

        Glide.with(holder.itemView.context)
            .load(meal.strCategoryThumb)
            .into(holder.mealImage)

        holder.btnDelete.setOnClickListener {
            movieListener.onMealClick(meal)
        }
    }

    override fun getItemCount(): Int = mealList.size
    
}