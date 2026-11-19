package com.example.myrecipestinorua

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myrecipestinorua.databinding.ActivityRecipeDetailBinding

const val RECIPE_ID_EXTRA_KEY = "recipe_id_extra_key"

class RecipeDetailActivity : AppCompatActivity() {
    private val LOGTAG = RecipeDetailActivity::class.simpleName
    private lateinit var binding: ActivityRecipeDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRecipeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Retrieve activity extras
        val recipeId = intent.extras?.getString(RECIPE_ID_EXTRA_KEY)
        Log.d(LOGTAG, "recipe id = $recipeId")
        binding.recipeIdTv.text = recipeId
    }
}
