package com.example.hellotechnonext.view.screen.postxcomments

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.hellotechnonext.coordinator.BaseChildNavGraph
import com.example.hellotechnonext.util.ComposeBaseExtensions
import com.example.hellotechnonext.view.MainViewModel

class PostUiMainScreen(private val navController: NavHostController) : BaseChildNavGraph {
    object Route {
        const val postscreen = "placeholder/post_screen"
    }

    override fun createChildNavGraphBuilder(): NavGraphBuilder.() -> Unit {
        val output: NavGraphBuilder.() -> Unit = {
            composable(Route.postscreen) {
                LoadPostsUi()
            }
        }
        return output
    }

    @Composable
    fun LoadPostsUi() {
        val mViewModel: MainViewModel = ComposeBaseExtensions.baseNavGraphViewModel(
            mNavHostController = navController,
            route = HomeScreen.Route.HomeScreen
        )

        val uiState by mViewModel.uiState.collectAsState()
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
                        text = uiState.error ?: "Api Error!!",
                        color = MaterialTheme.colorScheme.error,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = { }) {
                        Text("Retry")
                    }
                }
            }

            uiState.posts.isEmpty() -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No posts available")
                }
            }

            else -> {

                PostListScreen(uiState.posts)
            }
        }
    }
}

