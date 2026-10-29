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

class MainActivity : AppCompatActivity() {
    private val LOGTAG = MainActivity::class.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        setTitle(R.string.main_activity_title)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        loadCategories()
    }

    private fun loadCategories() {
        val api = RetrofitClient.create(IMealDbApi::class.java)
        lifecycleScope.launch {
            try {
                val response = api.getCategories()
                Log.d(LOGTAG, "Categories: ${response.categories.size}")
                for (category in response.categories) {
                    Log.d(LOGTAG, category.strCategory)
                }
            } catch (e: Exception) {
                if (e is CancellationException) throw e
                Log.e(LOGTAG, "Unable to load categories", e)
            }
        }
    }
}
