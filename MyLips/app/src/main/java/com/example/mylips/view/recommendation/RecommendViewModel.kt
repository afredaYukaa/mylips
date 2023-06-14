package com.example.mylips.view.recommendation

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mylips.retrofit.api.ApiConfig
import com.example.mylips.retrofit.response.GetStoriesResponse
import com.example.mylips.retrofit.response.ListStoryItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecommendViewModel : ViewModel() {

    private val _listStories = MutableLiveData<List<ListStoryItem>>()
    val listStories: LiveData<List<ListStoryItem>> = _listStories


    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private fun findRestaurant() {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getStoriesList("Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiJ1c2VyLXlqNXBjX0xBUkNfQWdLNjEiLCJpYXQiOjE2NDE3OTk5NDl9.flEMaQ7zsdYkxuyGbiXjEDXO8kuDTcI__3UjCwt6R_I")
        client.enqueue(object : Callback<GetStoriesResponse> {
            override fun onResponse(
                call: Call<GetStoriesResponse>,
                response: Response<GetStoriesResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {

                    _listStories.value = response.body()?.listStory
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<GetStoriesResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    companion object{

        private const val RESTAURANT_ID = "uewq1zg2zlskfw1e867"
    }
}