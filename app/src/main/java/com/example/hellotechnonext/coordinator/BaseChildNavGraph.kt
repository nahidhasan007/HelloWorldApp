package com.example.hellotechnonext.coordinator

import androidx.navigation.NavGraphBuilder

interface BaseChildNavGraph {
    fun createChildNavGraphBuilder() : (NavGraphBuilder.() -> Unit)
}