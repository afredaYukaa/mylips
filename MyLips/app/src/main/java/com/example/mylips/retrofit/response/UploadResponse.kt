package com.example.mylips.retrofit.response

import com.google.gson.annotations.SerializedName

data class UploadResponse(

	@field:SerializedName("link")
	val link: String,

	@field:SerializedName("name")
	val name: String
)
