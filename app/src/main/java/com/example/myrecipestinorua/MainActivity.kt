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
import com.example.myrecipestinorua.data.remote.Category
import com.example.myrecipestinorua.data.remote.IMealDbApi
import com.example.myrecipestinorua.data.remote.RetrofitClient
import com.example.myrecipestinorua.databinding.ActivityMainBinding
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val LOGTAG = MainActivity::class.simpleName

    private lateinit var binding: ActivityMainBinding
    private lateinit var categoryAdapter: CategoryAdapter
    private var categories = arrayListOf<Category>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // Init data binding
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        setTitle(R.string.main_activity_title)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Init recycler view
        binding.categoriesRv.setHasFixedSize(true)
        binding.categoriesRv.layoutManager = LinearLayoutManager(this)
        categoryAdapter = CategoryAdapter(categories)
        binding.categoriesRv.adapter = categoryAdapter

        loadCategories()
    }

    private fun loadCategories() {
        val api = RetrofitClient.create(IMealDbApi::class.java)
        lifecycleScope.launch {
            binding.loading = true
            try {
                val response = api.getCategories()
                categories.addAll(response.categories)
                categoryAdapter.notifyDataSetChanged()
                Log.d(LOGTAG, "Categories: ${response.categories.size}")
            } catch (e: Exception) {
                if (e is CancellationException) throw e
                Log.e(LOGTAG, "Unable to load categories", e)
            } finally {
                binding.loading = false
            }
        }
    }
}
