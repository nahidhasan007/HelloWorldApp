package com.example.postapp.data.repository

import com.example.postapp.data.remote.JsonPlaceholderApi
import com.example.postapp.servicegenerator.ApiServiceGenerator
import com.example.postapp.view.PostViewModelFactory

object DiContainer {
    private val apiServiceGenerator = ApiServiceGenerator()

    val apiService:  JsonPlaceholderApi by lazy {
        apiServiceGenerator.createService(JsonPlaceholderApi::class.java)
    }

    val postRepository: MainRepository by lazy {
        MainRepository(apiService)
    }

    val postViewModelFactory: PostViewModelFactory by lazy {
        PostViewModelFactory(postRepository)
    }
}