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
import com.example.musinsa.data.ContentsDto
import com.example.musinsa.data.DataDto
import com.example.musinsa.domain.FooterModel
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
                    scrollList = viewState.scrollList,
                    gridList = viewState.gridList,
                    currentGridCount = viewState.gridItemCount,
                    styleList = viewState.styleList,
                    currentStyleCount = viewState.styleItemCount,
                    footerClicked = { footer, data ->
                        viewModel.onAction(
                            when (footer) {
                                FooterModel.FooterType.REFRESH -> UserAction.Refresh(data)
                                FooterModel.FooterType.MORE -> UserAction.More(data)
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
    data: List<DataDto>,
    scrollList: List<ContentsDto.GoodsDto>,
    gridList: List<ContentsDto.GoodsDto>,
    styleList: List<ContentsDto.StyleDto>,
    currentGridCount: Int,
    currentStyleCount: Int,
    footerClicked: (
        type: FooterModel.FooterType,
        selectedContentDto: ContentsDto,
    ) -> Unit,
) {
    val density = LocalDensity.current
    var styleMatrixHeight by remember {
        mutableStateOf(0.dp)
    }
    LazyVerticalGrid(
        modifier = Modifier
            .padding(horizontal = 16.dp),
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        item(span = { GridItemSpan(3) }) {
            Spacer(modifier = Modifier.height(16.dp))
        }
        data.forEach {
            it.header?.let { header ->
                item(span = { GridItemSpan(3) }) {
                    HeaderComponent(header = header)
                }
            }
            when (it.content.type) {
                "BANNER" -> item(span = { GridItemSpan(3) }) {
                    BannerListComponent(
                        list = it.content.banners ?: emptyList()
                    )
                }
                "SCROLL" -> item(span = { GridItemSpan(3) }) {
                    ScrollListComponent(
                        list = scrollList
                    )
                }
                "GRID" -> gridGoods(
                    if (gridList.size <= currentGridCount) {
                        gridList
                    } else {
                        gridList.subList(0, currentGridCount)
                    }
                )
                "STYLE" -> {
                    styleItems(
                        items = if (styleList.size <= currentStyleCount) {
                            styleList
                        } else {
                            styleList.subList(0, currentStyleCount)
                        },
                        itemHeight = styleMatrixHeight,
                        onHeightChange = { dp ->
                            styleMatrixHeight = with(density) { dp.toDp() }
                        }
                    )
                }
            }
            it.footer?.let { footer ->
                item(span = { GridItemSpan(3) }) {
                    FooterComponent(footer = footer) {
                        footerClicked(
                            FooterModel.FooterType.valueOf(footer.type),
                            it.content
                        )
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

