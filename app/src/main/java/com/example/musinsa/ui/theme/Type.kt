package com.example.musinsa.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.example.musinsa.R

// Set of Material typography styles to start with
val Typography = Typography(
    defaultFontFamily = FontFamily(
        listOf(
            Font(R.font.musinsa_bold),
            Font(R.font.musinsa_medium),
            Font(R.font.musinsa_light)
        )
    ),
    h1 = TextStyle(
        fontFamily = FontFamily(Font(R.font.musinsa_medium)),
        fontSize = 24.sp
    ),
    body1 = TextStyle(
        fontFamily = FontFamily(Font(R.font.musinsa_medium)),
        fontSize = 16.sp
    ),
    body2 = TextStyle(
        fontFamily = FontFamily(Font(R.font.musinsa_medium)),
        fontSize = 12.sp,
    ),
    caption = TextStyle(
        fontFamily = FontFamily(Font(R.font.musinsa_medium)),
        fontSize = 10.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)