package com.example.mylips.view.main

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mylips.retrofit.api.ApiConfig
import com.example.mylips.retrofit.response.ListColorItem
import com.example.mylips.retrofit.response.RecommendResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val mToast = MutableLiveData<String>()
    val toast: LiveData<String> = mToast

    private val _listColor = MutableLiveData<List<ListColorItem>>()
    val listColor: LiveData<List<ListColorItem>> = _listColor



    fun uploadImage(imageMultipart: MultipartBody.Part, name: String) {
        val apiService = ApiConfig.getApiService()
        val uploadImageRequest = apiService.uploadImage(imageMultipart,name.toRequestBody())
        uploadImageRequest.enqueue(object : retrofit2.Callback<RecommendResponse> {
            override fun onResponse(
                call: retrofit2.Call<RecommendResponse>,
                response: retrofit2.Response<RecommendResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()

                    mToast.value = responseBody?.message
                    _listColor.value = responseBody?.listColor




                } else {
                    mToast.value = response.message()
                    Log.e("Error:","$response")
                }
            }

            override fun onFailure(call: Call<RecommendResponse>, t: Throwable) {
                mToast.value = t.message
                Log.e("Error: ","${t.message}")
            }
        })
    }

}