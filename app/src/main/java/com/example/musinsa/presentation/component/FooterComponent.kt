package com.example.musinsa.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.musinsa.data.FooterDto
import com.example.musinsa.ui.theme.Typography

@Composable
fun FooterComponent(
    footer: FooterDto,
    onItemClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = rememberRipple(),
                onClick = onItemClick
            ),
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(1.dp, color = Color.Gray),
    ) {
        Row(
            modifier = Modifier.padding(vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = footer.title,
                style = Typography.body1
            )
            footer.iconUrl?.let {
                Spacer(modifier = Modifier.width(4.dp))
                AsyncImage(
                    modifier = Modifier.size(16.dp),
                    model = it, contentDescription = "Icon URL"
                )
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}