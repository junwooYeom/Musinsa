package com.example.musinsa.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.dp
import com.example.musinsa.domain.ContentDetail

@Composable
fun ScrollListComponent(
    modifier: Modifier = Modifier,
    list: List<ContentDetail.Goods>,
) {
    LazyRow(
        modifier = Modifier.layout { measurable, constraints ->
            val placeable = measurable.measure(
                constraints.copy(
                    maxWidth = constraints.maxWidth + 32.dp.roundToPx(), //add the end padding 16.dp
                )
            )
            layout(placeable.width, placeable.height) {
                placeable.place(0, 0)
            }
        },
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
    ) {
        items(list) {
            GoodsItemComponent(
                modifier = modifier,
                goods = it
            )
        }
    }
}