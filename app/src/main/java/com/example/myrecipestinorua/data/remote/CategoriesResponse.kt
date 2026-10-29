package com.example.myrecipestinorua.data.remote

import com.google.gson.annotations.SerializedName


data class CategoriesResponse (

  @SerializedName("categories" ) var categories : ArrayList<Category> = arrayListOf()

)
