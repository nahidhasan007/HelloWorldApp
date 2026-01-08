package com.example.postapp.data.repository

import com.example.postapp.data.domain.Comment
import com.example.postapp.data.domain.Post
import com.example.postapp.data.remote.JsonPlaceholderApi
import retrofit2.Response

class MainRepository(private val apiService : JsonPlaceholderApi) {

    suspend fun getAllPosts(): Response<List<Post>> {
        return apiService.getAllPosts()
    }

    suspend fun getAllComments(): Response<List<Comment>> {
        return apiService.getAllComments()
    }


}