package com.example.myrecipestinorua

import com.example.myrecipestinorua.data.remote.Recipe

interface OnRecipeItemClickedListener {
    fun onRecipeItemClicked(recipe: Recipe)
}
