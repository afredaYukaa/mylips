package com.example.mylips.view.recommendation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mylips.R
import com.example.mylips.databinding.ActivityMainBinding
import com.example.mylips.databinding.ActivityRecommendBinding

class RecommendActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecommendBinding
    private lateinit var  rvStories: RecyclerView
    private val recommendViewModel by viewModels<RecommendViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecommendBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        setUpRv()
    }



    private fun setUpRv(){

        rvStories = binding.recyclerView
        rvStories.layoutManager = LinearLayoutManager(this)

        recommendViewModel.listStories.observe(this){ storyList ->
            val adapter = Adapter(storyList)
            rvStories.adapter = adapter
        }
    }
}