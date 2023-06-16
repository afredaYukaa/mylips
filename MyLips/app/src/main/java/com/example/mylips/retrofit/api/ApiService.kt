package com.example.mylips.retrofit.api





import com.example.mylips.retrofit.response.RecommendResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @Multipart
    @POST("upload")
    fun uploadImage(
        @Part file: MultipartBody.Part,
        @Part("filename") filename: RequestBody,
    ): Call<RecommendResponse>



}