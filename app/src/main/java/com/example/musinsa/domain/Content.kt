package com.example.musinsa.domain


data class ContentModel(
    val header: HeaderModel?,
    val footer: FooterModel?,
    val contents: Content,
)

data class HeaderModel(
    val title: String,
)

data class FooterModel(
    val type: FooterType,
    val title: String,
    val url: String,
) {
    enum class FooterType {
        REFRESH,
        MORE
    }
}

enum class ContentType {
    BANNER,
    GRID,
    SCROLL,
    STYLE
}

data class Content(
    val type: ContentType,
    val detail: List<ContentDetail>
)

sealed class ContentDetail {
    data class Goods(
        val linkUrl: String,
        val thumbnailUrl: String,
        val brandName: String,
        val price: Int,
        val saleRate: Int,
        val hasCoupon: Boolean) : ContentDetail()

    data class Style(
        val linkUrl: String,
        val thumbnailUrl: String
    ): ContentDetail()

    data class Banner(
        val linkUrl: String,
        val thumbnailUrl: String
    ): ContentDetail()
}