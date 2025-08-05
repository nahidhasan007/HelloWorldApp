package com.example.hellotechnonext.view.screen.postxcommentsdetails

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.hellotechnonext.coordinator.BaseChildNavGraph
import com.example.hellotechnonext.data.domain.Comment
import com.example.hellotechnonext.data.domain.Post
import com.example.hellotechnonext.view.screen.postxcomments.CommentCard
import com.example.hellotechnonext.view.screen.postxcomments.PostCard
import com.google.gson.Gson

class PostCommentDetailScreen(private val navController: NavHostController) : BaseChildNavGraph {

    object Route {
        const val detailScreen = "placeholder/detail_screen"
    }

    override fun createChildNavGraphBuilder(): NavGraphBuilder.() -> Unit = {
        composable(Route.detailScreen) { backStackEntry ->
            LoadDetailPostXComment()
        }
    }

    @Composable
    fun LoadDetailPostXComment() {
        val previousEntry = navController.previousBackStackEntry
        val gson = remember { Gson() }

        // Log for debugging
        Log.d("TAG", "Previous Entry: $previousEntry")

        val postJson = previousEntry?.savedStateHandle?.get<String>("post")
        val commentJson = previousEntry?.savedStateHandle?.get<String>("comment")

        val post = remember(postJson) {
            postJson?.let {
                runCatching { gson.fromJson(it, Post::class.java) }.getOrNull()
            }
        }

        val comment = remember(commentJson) {
            commentJson?.let {
                runCatching { gson.fromJson(it, Comment::class.java) }.getOrNull()
            }
        }

        when {
            post != null -> PostCard(post)
            comment != null -> CommentCard(comment)
            else -> NoDataFound()
        }
    }

    @Composable
    private fun NoDataFound() {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("No data available")
        }
    }
}
