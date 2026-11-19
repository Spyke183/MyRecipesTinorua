package com.example.myrecipestinorua.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

// https://www.themealdb.com/api/json/v1/1/categories.php
// https://www.themealdb.com/api/json/v1/1/filter.php?c=Seafood

interface IMealDbApi {
    @GET("categories.php")
    suspend fun getCategories() : CategoriesResponse

    @GET("filter.php")
    suspend fun getRecipesByCategory(@Query("c") category: String) : RecipesResponse
}
