package com.example.hellotechnonext.view.screen.postxcomments

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.hellotechnonext.data.domain.Tab

@Composable
fun TabSelector(
    selectedTab: Tab,
    onTabSelected: (Tab) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Button(
            onClick = { onTabSelected(Tab.POSTS) },
            enabled = selectedTab != Tab.POSTS
        ) {
            Text("Posts")
        }

        Button(
            onClick = { onTabSelected(Tab.COMMENTS) },
            enabled = selectedTab != Tab.COMMENTS
        ) {
            Text("Comments")
        }

        Button(
            onClick = { onTabSelected(Tab.OTHER) },
            enabled = selectedTab != Tab.OTHER
        ) {
            Text("other")
        }
    }
}
