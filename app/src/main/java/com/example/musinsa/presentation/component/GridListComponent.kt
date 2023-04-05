package com.example.musinsa.presentation.component

import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.items
import com.example.musinsa.data.ContentsDto

internal fun LazyGridScope.gridGoods(
    itemList: List<ContentsDto.GoodsDto>
) {
    items(itemList) {
        GoodsItemComponent(goods = it)
    }

}