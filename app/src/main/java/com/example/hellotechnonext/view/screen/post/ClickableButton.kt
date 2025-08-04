package com.example.hellotechnonext.view.screen.post

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ClickableButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String = "Click Me",
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled
    ) {
        Text(text = text)
    }
}