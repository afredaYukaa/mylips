package com.example.mylips.retrofit.response

import com.google.gson.annotations.SerializedName


data class RecommendResponse(

    @field:SerializedName("ListColor")
    val listColor: List<ListColorItem>,

    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String
)

data class ListColorItem(

    @field:SerializedName("link")
    val link: String,

    @field:SerializedName("name")
    val name: String,



)