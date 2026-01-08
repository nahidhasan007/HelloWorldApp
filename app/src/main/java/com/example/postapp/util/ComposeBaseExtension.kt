package com.example.postapp.util

import android.content.Context
import android.content.ContextWrapper
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController

object ComposeBaseExtensions {
    fun routeWithArgs(someRoute: String, pathArgs: List<Any> = emptyList()): String {
        var output: String = someRoute
        for(arg in pathArgs) {
            output += "/{${arg}}"
        }
        return output
    }

    fun routeWithInputVariables(someRoute: String, inputVariableArgs: List<Any> = emptyList()): String {
        var output: String = someRoute
        for(arg in inputVariableArgs) {
            output += "/${arg}"
        }
        return output
    }

    @Composable
    inline fun <reified T: ViewModel>baseNavGraphViewModel(mNavHostController: NavHostController, route: String, factory: ViewModelProvider.Factory? = null): T {
        val rememberBackStackEntry: NavBackStackEntry = mNavHostController.getBackStackEntry(route) /*remember(mNavHostController.currentBackStackEntry) {

    }*/

        val mViewModel: T = viewModel(T::class, rememberBackStackEntry, factory = factory)
        return mViewModel
    }

    fun Context.findActivity(): ComponentActivity? = when (this) {
        is ComponentActivity -> this
        is ContextWrapper -> baseContext.findActivity()
        else -> null
    }
}