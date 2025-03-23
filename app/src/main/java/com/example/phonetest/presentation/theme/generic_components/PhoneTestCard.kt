package com.example.phonetest.presentation.theme.generic_components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.phonetest.presentation.theme.containerItemColor

@Composable
fun PhoneTestCard(text: String, icon: Int, modifier: Modifier, onClick: () -> Unit) {
    val screensWithNoTint = setOf("Green Screen", "Red Screen", "Blue Screen", "Black Screen")
    Card(
        onClick = onClick,
        modifier = modifier
            .padding(8.dp)
            .aspectRatio(1f),
        colors = CardDefaults.cardColors(containerColor = containerItemColor),
        elevation = CardDefaults.elevatedCardElevation(6.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(
                painter = painterResource(icon),
                contentDescription = text,
                modifier = Modifier
                    .size(32.dp)
                    .align(Alignment.CenterHorizontally),
                tint = if (text in screensWithNoTint) Color.Unspecified else Color(0xFFF0F0F0)
            )

            Text(
                text = text,
                color = Color(0xFF7C7C7C),
                fontSize = 14.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}