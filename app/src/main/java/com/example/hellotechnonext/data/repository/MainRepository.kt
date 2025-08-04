package com.example.hellotechnonext.data.repository

import com.example.hellotechnonext.data.domain.Comment
import com.example.hellotechnonext.data.domain.Post
import com.example.hellotechnonext.data.remote.JsonPlaceholderApi
import retrofit2.Response

class MainRepository(private val apiService : JsonPlaceholderApi) {

    suspend fun getAllPosts(): Response<List<Post>> {
        return apiService.getAllPosts()
    }

    suspend fun getAllComments(): Response<List<Comment>> {
        return apiService.getAllComments()
    }


}