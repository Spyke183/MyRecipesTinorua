package com.example.myrecipestinorua

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myrecipestinorua.data.remote.Recipe
import com.example.myrecipestinorua.databinding.RecipeItemBinding

class RecipeAdapter(private val recipes: ArrayList<Recipe>) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    private var onRecipeItemClickedListener: OnRecipeItemClickedListener? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = RecipeViewHolder(
        RecipeItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) =
        holder.bind(recipes[position])

    override fun getItemCount(): Int = recipes.size

    inner class RecipeViewHolder(private val binding: RecipeItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(recipe: Recipe) {
            binding.recipe = recipe
            binding.recipeItemCl.setOnClickListener {
                onRecipeItemClickedListener?.onRecipeItemClicked(recipe)
            }
            binding.executePendingBindings()
        }
    }

    fun setRecipeItemClickedListener(listener: OnRecipeItemClickedListener) {
        this.onRecipeItemClickedListener = listener
    }
}
