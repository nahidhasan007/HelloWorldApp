package com.example.hellotechnonext.view.screen.post

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.hellotechnonext.coordinator.BaseChildNavGraph
import com.example.hellotechnonext.data.repository.DiContainer
import com.example.hellotechnonext.view.MainViewModel

class HomeScreen(private val navController: NavHostController) : BaseChildNavGraph {
    object Route {
        const val HomeScreen = "placeholder/home_screen"
    }

    override fun createChildNavGraphBuilder(): NavGraphBuilder.() -> Unit {
        val output: NavGraphBuilder.() -> Unit = {
            composable(HomeScreen.Route.HomeScreen) {
                LoadPostUi()
            }
        }
        return output
    }


    @Composable
    fun LoadPostUi() {
        val viewModel: MainViewModel = viewModel(factory = DiContainer.postViewModelFactory)
        val uiState by viewModel.uiState.collectAsState()
        val uiStateComments by viewModel.uiStateComments.collectAsState()

        var selectedTab by remember { mutableStateOf(Tab.POSTS) }

        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(onClick = {
                    selectedTab = Tab.POSTS
                    viewModel.fetchAllPosts()
                }) {
                    Text("Load Posts from API")
                }

                Button(onClick = {
                    selectedTab = Tab.COMMENTS
                    viewModel.fetchAllComments() // You need this method implemented in your ViewModel
                }) {
                    Text("Load Comments from API")
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            when {
                uiState.isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                uiState.error != null -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = uiState.error ?: "API Error!!",
                            color = MaterialTheme.colorScheme.error,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick = {
                            // Retry fetch based on selected tab
                            if (selectedTab == Tab.POSTS) viewModel.fetchAllPosts()
                            else viewModel.fetchAllComments()
                        }) {
                            Text("Retry")
                        }
                    }
                }

                selectedTab == Tab.POSTS && uiState.posts.isEmpty() -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("No posts available")
                    }
                }

                selectedTab == Tab.COMMENTS && uiStateComments.isLoading-> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                else -> {
                    if (selectedTab == Tab.POSTS) {
                        PostListScreen(uiState.posts)
                    } else {
                        CommentListScreen(uiStateComments.comments)
                    }
                }
            }
        }
    }

    enum class Tab {
        POSTS,
        COMMENTS
    }
}