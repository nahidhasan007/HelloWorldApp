package com.example.postapp.data.remote

import com.example.postapp.data.domain.Comment
import com.example.postapp.data.domain.Post
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface JsonPlaceholderApi {
    @GET("posts")
    suspend fun getAllPosts(): Response<List<Post>>

    @GET("posts/{id}")
    suspend fun getPost(@Path("id") postId: Int): Response<Post>

    @GET("comments")
    suspend fun getAllComments(): Response<List<Comment>>

    @GET("posts/{id}/comments")
    suspend fun getCommentsForPost(@Path("id") postId: Int): Response<List<Comment>>
}