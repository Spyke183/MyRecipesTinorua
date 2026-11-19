package com.example.myrecipestinorua

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myrecipestinorua.data.remote.IMealDbApi
import com.example.myrecipestinorua.data.remote.Recipe
import com.example.myrecipestinorua.data.remote.RetrofitClient
import com.example.myrecipestinorua.databinding.ActivityRecipesBinding
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch

const val CATEGORY_NAME_EXTRA_KEY = "category_name_extra_key"

class RecipesActivity : AppCompatActivity() {
    private val LOGTAG = RecipesActivity::class.simpleName

    private lateinit var binding: ActivityRecipesBinding
    private lateinit var recipeAdapter: RecipeAdapter
    private var recipes = arrayListOf<Recipe>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // Init data binding
        binding = DataBindingUtil.setContentView<ActivityRecipesBinding>(this, R.layout.activity_recipes)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Retrieve activity extras
        val categoryName = intent.extras?.getString(CATEGORY_NAME_EXTRA_KEY) ?: ""
        setTitle(getString(R.string.recipes_activity_title, categoryName))

        // Init recycler view
        binding.recipesRv.setHasFixedSize(true)
        binding.recipesRv.layoutManager = LinearLayoutManager(this)
        recipeAdapter = RecipeAdapter(recipes)
        binding.recipesRv.adapter = recipeAdapter

        loadRecipes(categoryName)
    }

    private fun loadRecipes(categoryName: String) {
        val api = RetrofitClient.create(IMealDbApi::class.java)
        lifecycleScope.launch {
            try {
                val response = api.getRecipesByCategory(categoryName)
                Log.d(LOGTAG, "Recipes: ${response.meals.size}")
                for (recipe in response.meals) {
                    Log.d(LOGTAG, recipe.strMeal)
                }
            } catch (e: Exception) {
                if (e is CancellationException) throw e
                Log.e(LOGTAG, "Unable to load recipes", e)
            }
        }
    }
}
