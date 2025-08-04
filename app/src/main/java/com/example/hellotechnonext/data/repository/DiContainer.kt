package com.example.hellotechnonext.data.repository

import com.example.hellotechnonext.data.remote.JsonPlaceholderApi
import com.example.hellotechnonext.servicegenerator.ApiServiceGenerator
import com.example.hellotechnonext.view.PostViewModelFactory

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