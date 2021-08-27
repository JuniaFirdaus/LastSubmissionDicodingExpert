package com.dicoding.foodsmapp.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class CategoryFoodResponse(

	@field:SerializedName("categories")
	val categories: List<CategoriesItem>
)