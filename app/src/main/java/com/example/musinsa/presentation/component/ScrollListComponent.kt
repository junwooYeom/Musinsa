package com.example.musinsa.presentation.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.musinsa.domain.ContentDetail

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ScrollListComponent(
    list: List<ContentDetail.Goods>
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(list) {
            GoodsItemComponent(
                modifier = Modifier.animateItemPlacement(),
                goods = it
            )
        }
    }
}