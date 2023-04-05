package com.example.musinsa.presentation.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    val pagerState = rememberPagerState()
    LaunchedEffect(Unit) {
        delay(2000)
        pagerState.animateScrollToPage((pagerState.currentPage + 1).mod(list.size))
    }
    HorizontalPager(pageCount = list.size, state = pagerState) {
        val currentItem = list[it]
        Box(
            contentAlignment = Alignment.BottomCenter
        ) {
            AsyncImage(
                model = list[it].thumbnailUrl,
                contentDescription = "Banner Image"
            )

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (currentItem.title.isNotEmpty()) {
                    Text(
                        text = currentItem.title,
                        style = Typography.h1,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
                if (currentItem.description.isNotEmpty()) {
                    Text(
                        text = currentItem.description,
                        style = Typography.body1,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }

    }
}