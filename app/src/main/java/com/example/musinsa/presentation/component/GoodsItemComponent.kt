package com.example.musinsa.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.musinsa.domain.ContentDetail
import com.example.musinsa.ui.theme.Typography

@Composable
fun GoodsItemComponent(
    modifier: Modifier = Modifier,
    goods: ContentDetail.Goods
) {
    Column(modifier = modifier){
        Box(contentAlignment = Alignment.BottomStart) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = goods.thumbnailUrl,
                contentDescription = "thumbnail_url"
            )
            if (goods.hasCoupon) {
                Surface(
                    color = Color.Blue,
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text(
                        modifier = Modifier.padding(4.dp),
                        text = "쿠폰",
                        color = Color.White,
                        style = Typography.caption
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        if (goods.brandName.isNotEmpty()) {
            Text(
                text = goods.brandName,
                style = Typography.body2,
                color = Color.Gray
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                overflow = TextOverflow.Ellipsis,
                text = "${goods.price}원",
            )
            Text(
                text = "${goods.saleRate}%",
                color = Color.Red
            )
        }
    }
}