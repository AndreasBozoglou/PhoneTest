package com.example.phonetest.presentation.ui.features.generic_components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.phonetest.presentation.theme.backgroundColor
import com.example.phonetest.presentation.theme.textColor

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    topBarText: String,
    showIcon: Boolean,
    icon: Int? = null,
    onIconClick: (() -> Unit)? = null
) {
    Row(
        modifier = modifier
            .heightIn(55.dp)
            .fillMaxWidth()
            .background(color = backgroundColor)
            .windowInsetsPadding(WindowInsets.statusBars)
            .padding(horizontal = 13.5.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (showIcon && icon != null) {
            Icon(
                painter = painterResource(icon),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.clickable {
                    if (onIconClick != null) {
                        onIconClick()
                    }
                }
            )
        }
        Text(
            text = topBarText,
            color = textColor,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
    }
}