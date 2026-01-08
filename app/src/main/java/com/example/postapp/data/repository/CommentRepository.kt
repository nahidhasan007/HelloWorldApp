package com.example.postapp.data.repository

import com.example.postapp.data.domain.Comment

class CommentRepository {

    suspend fun getComments() : List<Comment>{
        return emptyList()
    }
}