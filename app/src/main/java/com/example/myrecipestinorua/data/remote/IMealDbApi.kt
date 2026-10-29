package com.example.myrecipestinorua.data.remote

import retrofit2.http.GET

// https://www.themealdb.com/api/json/v1/1/categories.php

interface IMealDbApi {
    @GET("categories.php")
    suspend fun getCategories() : CategoriesResponse
}
