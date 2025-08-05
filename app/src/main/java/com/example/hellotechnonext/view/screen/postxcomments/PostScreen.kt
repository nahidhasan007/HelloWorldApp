package com.example.hellotechnonext.view.screen.postxcomments

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.hellotechnonext.data.domain.Comment
import com.example.hellotechnonext.data.domain.Post

@Composable
fun PostListScreen(posts: List<Post>, onItemClick: (Post) -> Unit = {}) {
    LazyColumn{
        itemsIndexed(posts) {  index, post->
            PostCard(post = post){
                onItemClick(post)
            }
        }
    }
}

@Composable
fun CommentListScreen(comments: List<Comment>, onItemClick: (Comment) -> Unit) {
    LazyColumn{
        itemsIndexed(comments) {  index, comment->
            CommentCard(comment = comment, {
                onItemClick(comment)
            })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPostList() {
    val samplePosts = listOf(
        Post(1, 1, "Jetpack Compose", "Jetpack Compose simplifies UI development on Android."),
        Post(2, 2, "Kotlin DSL", "Kotlin makes Android development concise and expressive."),
        Post(3, 3, "LazyColumn", "A performant way to show lists in Compose.")
    )

    MaterialTheme {
        PostListScreen(posts = samplePosts)
    }
}

