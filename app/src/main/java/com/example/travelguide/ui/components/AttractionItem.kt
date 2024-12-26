package com.example.travelguide.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.travelguide.data.db.entities.Attraction

@Composable
fun AttractionItem(
    attraction: Attraction,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier.padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = attraction.title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Text(text = "Местоположение: ${attraction.location}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Тип: ${attraction.type}", style = MaterialTheme.typography.bodyMedium)

        }
    }
}
