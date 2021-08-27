package com.dicoding.foodsmapp.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class CategoriesItem(

	@field:SerializedName("strCategory")
	val strCategory: String,

	@field:SerializedName("strCategoryDescription")
	val strCategoryDescription: String,

	@field:SerializedName("idCategory")
	val idCategory: String,

	@field:SerializedName("strCategoryThumb")
	val strCategoryThumb: String
)