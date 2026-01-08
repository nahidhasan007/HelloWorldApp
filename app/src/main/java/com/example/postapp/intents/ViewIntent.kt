package com.example.postapp.intents

sealed class ViewIntent {
    object LoadPosts : ViewIntent()
    object LoadComments : ViewIntent()

    object LoadOther : ViewIntent()
}
