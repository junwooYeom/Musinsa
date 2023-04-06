package com.example.musinsa.presentation.component

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.musinsa.domain.ContentDetail
import com.example.musinsa.ui.theme.Typography
import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BannerListComponent(
    list: List<ContentDetail.Banner>,
) {
    var currentCount by remember {
        mutableStateOf(1)
    }

    val pagerState = rememberPagerState()
    LaunchedEffect(Unit) {
        while (true) {
            delay(3000)
            pagerState.animateScrollToPage(
                (pagerState.currentPage + 1).mod(list.size),
                animationSpec = tween(600)
            )
        }
    }

    Box(
        contentAlignment = Alignment.BottomEnd,
    ) {
        HorizontalPager(
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
            pageCount = Int.MAX_VALUE,
            state = pagerState,
            beyondBoundsPageCount = 1,
        ) {
            currentCount = (it + 1) % list.size
            val currentItem = list[currentCount]
            Box(
                contentAlignment = Alignment.BottomCenter,
            ) {
                AsyncImage(
                    modifier = Modifier.fillMaxWidth(),
                    model = currentItem.thumbnailUrl,
                    contentScale = ContentScale.Crop,
                    contentDescription = "Banner Image"
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .graphicsLayer {
                            translationX = 0.5f * pagerState.currentPageOffsetFraction
                        },
                ) {
                    if (currentItem.title.isNotEmpty()) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .graphicsLayer {

                                },
                            text = currentItem.title,
                            style = Typography.h1,
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                    if (currentItem.description.isNotEmpty()) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth(),
                            text = currentItem.description,
                            style = Typography.body1,
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                    }
                }
            }

        }

        Surface(
            color = Color.Gray.copy(alpha = 0.4f),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                modifier = Modifier.padding(
                    horizontal = 8.dp,
                    vertical = 4.dp
                ),
                text = "${pagerState.currentPage + 1} / ${list.size}",
                style = Typography.body1,
                color = Color.White
            )

        }
    }
}


