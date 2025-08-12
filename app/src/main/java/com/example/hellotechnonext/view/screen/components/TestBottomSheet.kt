package com.example.hellotechnonext.view.screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeliveryTimeBottomSheet(
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit,
    onDelete: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)
    var selectedDateIndex by remember { mutableStateOf(0) }
    var selectedTime by remember { mutableStateOf("04:00 - 04:30 PM") }

    val dates = listOf(
        "09 Apr\nToday",
        "10 Apr\nTomorrow",
        "11 Apr\nFri",
        "12 Apr\nSat",
        "13 Apr\nSun",
        "14 Apr\nMon"
    )

    val times = listOf(
        "04:00 - 04:30 PM",
        "04:30 - 05:00 PM",
        "05:00 - 05:30 PM",
        "05:30 - 06:00 PM",
        "06:00 - 06:30 PM",
        "06:30 - 07:00 PM"
    )

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        dragHandle = null,
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Title & Close button
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Select your delivery time", style = MaterialTheme.typography.titleMedium)
                IconButton(onClick = onDismiss) {
                    Icon(Icons.Default.Close, contentDescription = "Close")
                }
            }

            Spacer(Modifier.height(8.dp))

            // Scrollable Dates Row
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                itemsIndexed(dates) { index, text ->
                    Column(
                        modifier = Modifier
                            .clickable { selectedDateIndex = index }
                            .padding(vertical = 8.dp)
                            .width(64.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = text,
                            style = MaterialTheme.typography.bodySmall,
                            color = if (selectedDateIndex == index) Color.Red else Color.Gray,
                            textAlign = TextAlign.Center
                        )
                        if (selectedDateIndex == index) {
                            Box(
                                modifier = Modifier
                                    .padding(top = 4.dp)
                                    .height(2.dp)
                                    .width(24.dp)
                                    .background(Color.Red)
                            )
                        }
                    }
                }
            }

            Spacer(Modifier.height(16.dp))

            // Scrollable Time Slots (Centered)
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp), // Scroll area height
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(times) { time ->
                    val isSelected = time == selectedTime
                    Box(
                        modifier = Modifier
                            .padding(vertical = 6.dp)
                            .background(
                                if (isSelected) Color(0xFFFFEBEE) else Color.Transparent,
                                RoundedCornerShape(8.dp)
                            )
                            .clickable { selectedTime = time }
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        Text(
                            text = time,
                            color = if (isSelected) Color.Red else Color.Gray,
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Spacer(Modifier.height(16.dp))

            // Action buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = onDelete,
                    colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray)
                ) {
                    Text("Delete", color = Color.Black)
                }
                Button(
                    onClick = { onConfirm(selectedTime) },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                ) {
                    Text("Confirm", color = Color.White)
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun DeliveryTimeBottomSheetPreview() {
    DeliveryTimeBottomSheet(
        onDismiss = {},
        onConfirm = {},
        onDelete = {}
    )
}
