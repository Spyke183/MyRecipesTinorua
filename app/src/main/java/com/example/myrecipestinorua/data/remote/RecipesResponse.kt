package com.example.myrecipestinorua.data.remote

import com.google.gson.annotations.SerializedName


data class RecipesResponse (

  @SerializedName("meals" ) var meals : ArrayList<Recipe> = arrayListOf()

)
