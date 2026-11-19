package com.example.myrecipestinorua.data.remote

import com.google.gson.annotations.SerializedName

data class Recipe (

  @SerializedName("strMeal"      ) var strMeal      : String,
  @SerializedName("strMealThumb" ) var strMealThumb : String,
  @SerializedName("idMeal"       ) var idMeal       : String

)
