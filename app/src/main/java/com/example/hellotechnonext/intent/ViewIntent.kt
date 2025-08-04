package com.example.hellotechnonext.intent

sealed class ViewIntent {
    object LoadPosts : ViewIntent()
    object LoadComments : ViewIntent()
}
