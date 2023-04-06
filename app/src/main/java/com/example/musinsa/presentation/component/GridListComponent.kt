package com.example.musinsa.presentation.component

import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.items
import com.example.musinsa.domain.ContentDetail

internal fun LazyGridScope.gridGoods(
    items: List<ContentDetail.Goods>
) {
    items(items) {
        GoodsItemComponent(
            goods = it
        )
    }
}