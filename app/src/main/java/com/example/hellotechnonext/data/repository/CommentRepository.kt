package com.example.hellotechnonext.data.repository

import com.example.hellotechnonext.data.domain.Comment

class CommentRepository {

    suspend fun getComments() : List<Comment>{
        return emptyList()
    }
}