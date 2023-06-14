package com.example.mylips.retrofit.api


import com.example.mylips.retrofit.response.GetStoriesResponse
import com.example.mylips.retrofit.response.UploadResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @Multipart
    @POST("upload")
    fun uploadImage(
        @Part file: MultipartBody.Part,
        @Part("filename") filename: RequestBody,
    ): Call<UploadResponse>


    @GET("stories")
    fun getStoriesList(
        @Header("Authorization") token: String,
    ): Call<GetStoriesResponse>
}