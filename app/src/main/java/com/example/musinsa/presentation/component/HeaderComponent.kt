package com.example.musinsa.presentation.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.musinsa.data.HeaderDto
import com.example.musinsa.ui.theme.Typography

@Composable
fun HeaderComponent(
    modifier: Modifier = Modifier,
    header: HeaderDto
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(3f),
            text = header.title,
            style = Typography.h1,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2
        )
        Spacer(modifier = Modifier.width(4.dp))
        header.iconUrl?.let {
            AsyncImage(
                model = it,
                contentDescription = "Header Icon"
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        header.linkUrl?.let {
            Text(
                text = "전체",
                style = Typography.body1,
                color = Color.Gray
            )
        }
    }
}