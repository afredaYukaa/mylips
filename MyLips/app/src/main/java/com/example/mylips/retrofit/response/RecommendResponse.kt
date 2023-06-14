package com.example.mylips.retrofit.response

import com.google.gson.annotations.SerializedName

data class GetStoriesResponse(

    @field:SerializedName("listStory")
    val listStory: List<ListStoryItem>,

    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String
)

data class ListStoryItem(

    @field:SerializedName("photoUrl")
    val photoUrl: String,



    @field:SerializedName("name")
    val name: String,



    @field:SerializedName("id")
    val id: String,


    )
