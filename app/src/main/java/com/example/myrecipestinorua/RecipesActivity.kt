package com.example.myrecipestinorua

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.myrecipestinorua.data.remote.IMealDbApi
import com.example.myrecipestinorua.data.remote.RetrofitClient
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch

const val CATEGORY_NAME_EXTRA_KEY = "category_name_extra_key"

class RecipesActivity : AppCompatActivity() {
    private val LOGTAG = RecipesActivity::class.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_recipes)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Retrieve activity extras
        val categoryName = intent.extras?.getString(CATEGORY_NAME_EXTRA_KEY) ?: ""
        setTitle(getString(R.string.recipes_activity_title, categoryName))

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
