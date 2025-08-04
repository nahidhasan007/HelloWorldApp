package com.example.hellotechnonext.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hellotechnonext.data.domain.CommentUiState
import com.example.hellotechnonext.data.domain.PostUiState
import com.example.hellotechnonext.data.repository.MainRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.example.hellotechnonext.intent.ViewIntent

class MainViewModel(private val repository: MainRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(PostUiState())
    val uiState: StateFlow<PostUiState> = _uiState.asStateFlow()

    private val _uiStateComments = MutableStateFlow(CommentUiState())
    val uiStateComments: StateFlow<CommentUiState> = _uiStateComments.asStateFlow()


    fun handleIntent(intent: ViewIntent) {
        viewModelScope.launch {
            when (intent) {
                is ViewIntent.LoadPosts -> fetchAllPosts()
                is ViewIntent.LoadComments -> fetchAllComments()
            }
        }
    }


    private suspend fun fetchAllPosts() {
        _uiState.value = _uiState.value.copy(isLoading = true, error = null)

        try {
            val response = repository.getAllPosts()
            if (response.isSuccessful) {
                _uiState.value = _uiState.value.copy(
                    posts = response.body() ?: emptyList(),
                    isLoading = false
                )
            } else {
                _uiState.value = _uiState.value.copy(
                    error = "Error: ${response.code()} ${response.message()}",
                    isLoading = false
                )
            }
        } catch (e: Exception) {
            _uiState.value = _uiState.value.copy(
                error = "Network error: ${e.message}",
                isLoading = false
            )
        }
    }

    private suspend fun fetchAllComments() {
        _uiStateComments.value = _uiStateComments.value.copy(isLoading = true, error = null)

        try {
            val response = repository.getAllComments()
            if (response.isSuccessful) {
                _uiStateComments.value = _uiStateComments.value.copy(
                    comments = response.body() ?: emptyList(),
                    isLoading = false
                )
            } else {
                _uiStateComments.value = _uiStateComments.value.copy(
                    error = "Error: ${response.code()} ${response.message()}",
                    isLoading = false
                )
            }
        } catch (e: Exception) {
            _uiStateComments.value = _uiStateComments.value.copy(
                error = "Network error: ${e.message}",
                isLoading = false
            )
        }
    }
}