package com.example.musinsa.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.airbnb.mvrx.Fail
import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.Uninitialized
import com.airbnb.mvrx.compose.collectAsState
import com.airbnb.mvrx.compose.mavericksViewModel
import com.example.musinsa.domain.Content
import com.example.musinsa.domain.ContentDetail
import com.example.musinsa.domain.ContentType
import com.example.musinsa.domain.Data
import com.example.musinsa.domain.FooterType
import com.example.musinsa.presentation.component.BannerListComponent
import com.example.musinsa.presentation.component.FooterComponent
import com.example.musinsa.presentation.component.gridGoods
import com.example.musinsa.presentation.component.HeaderComponent
import com.example.musinsa.presentation.component.ScrollListComponent
import com.example.musinsa.presentation.component.styleItems
import com.example.musinsa.ui.theme.Typography

@Composable
fun MainScreen(
    viewModel: ListViewModel = mavericksViewModel(),
) {
    val viewState by viewModel.collectAsState()
    when (viewState.contentModel) {
        is Loading, Uninitialized -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        is Success -> {
            Column {
                ItemScreen(
                    data = viewState.contentList,
                    footerClicked = { footer, data ->
                        viewModel.onAction(
                            when (footer) {
                                FooterType.REFRESH -> UserAction.Refresh(data)
                                FooterType.MORE -> UserAction.More(data)
                            }
                        )
                    })
            }

        }
        is Fail -> {
            Column {
                Text(
                    text = "불러오기에 실패하였습니다!",
                    style = Typography.h1
                )
            }
        }
    }
}

@Composable
private fun ItemScreen(
    data: List<Data>,
    footerClicked: (
        type: FooterType,
        selectedContentDto: Content,
    ) -> Unit,
) {
    val density = LocalDensity.current
    var styleMatrixHeight by remember {
        mutableStateOf(0.dp)
    }
    LazyVerticalGrid(
        modifier = Modifier.padding(16.dp),
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        data.forEach {
            it.header?.let { header ->
                item(span = { GridItemSpan(3) }) {
                    HeaderComponent(
                        header = header
                    )
                }
            }
            when (it.contents.type) {
                ContentType.BANNER -> item(span = { GridItemSpan(3) }) {
                    BannerListComponent(
                        list = it.contents.filteredDetail.map { detail ->
                            detail as ContentDetail.Banner
                        }
                    )
                }
                ContentType.SCROLL -> item(span = { GridItemSpan(3) }) {
                    ScrollListComponent(
                        list = it.contents.filteredDetail.map { detail ->
                            detail as ContentDetail.Goods
                        }
                    )
                }
                ContentType.GRID ->
                    gridGoods(
                        items = it.contents.filteredDetail.map { detail ->
                            detail as ContentDetail.Goods
                        }
                    )
                ContentType.STYLE ->
                    styleItems(
                        items = it.contents.filteredDetail.map { contentDetail ->
                            contentDetail as ContentDetail.Style
                        },
                        itemHeight = styleMatrixHeight,
                        onHeightChange = { dp ->
                            styleMatrixHeight = with(density) { dp.toDp() }
                        }
                    )
            }
            item(span = { GridItemSpan(3) }) {
                Spacer(modifier = Modifier.height(12.dp))
            }
            it.footer?.let { footer ->
                if (it.isFooterShown) {
                    item(span = { GridItemSpan(3) }) {
                        FooterComponent(
                            footer = footer
                        ) {
                            footerClicked(
                                footer.type,
                                it.contents
                            )
                        }
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
        item {
            Spacer(modifier = Modifier.height(34.dp))
        }
    }
}

