package com.example.musinsa.presentation.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.example.musinsa.domain.ContentDetail

@Composable
internal fun StyleItemComponent(
    modifier: Modifier = Modifier,
    style: ContentDetail.Style,
) {
    AsyncImage(
        modifier = modifier.fillMaxSize(),
        model = style.thumbnailUrl,
        contentScale = ContentScale.Crop,
        contentDescription = "URLThumbnail"
    )
}