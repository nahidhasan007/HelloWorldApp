package com.example.postapp.coordinator

import androidx.navigation.NavGraphBuilder

interface BaseChildNavGraph {
    fun createChildNavGraphBuilder() : (NavGraphBuilder.() -> Unit)
}