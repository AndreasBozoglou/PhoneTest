package com.example.phonetest.presentation.theme.generic_components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PhoneTestCard(text: String, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .padding(8.dp)
            .size(110.dp),
        colors = CardDefaults.cardColors(containerColor = Color.DarkGray),
        elevation = CardDefaults.elevatedCardElevation(6.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(
                Icons.Outlined.Add,
                contentDescription = text,
                modifier = Modifier
                    .size(32.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Text(
                text = text,
                color = Color.Black,
                fontSize = 14.sp,
                maxLines = 1,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}