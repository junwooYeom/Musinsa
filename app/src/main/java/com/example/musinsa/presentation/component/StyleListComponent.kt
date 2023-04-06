package com.example.musinsa.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.musinsa.domain.ContentDetail

internal fun LazyGridScope.styleItems(
    items: List<ContentDetail.Style>,
    itemHeight: Dp,
    onHeightChange: (Int) -> Unit,
) {
    item(span = { GridItemSpan(3) }) {
        Row(

            horizontalArrangement = Arrangement.spacedBy(
                8.dp,
                alignment = Alignment.CenterHorizontally
            )
        ) {
            StyleItemComponent(
                modifier = Modifier
                    .weight(2f)
                    .height(itemHeight),
                style = items[0],
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .onGloballyPositioned {
                        onHeightChange(it.size.height)
                    },
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                StyleItemComponent(style = items[1])
                StyleItemComponent(style = items[2])
            }
        }
    }

    items(items.drop(3)) {
        StyleItemComponent(style = it)
    }
}