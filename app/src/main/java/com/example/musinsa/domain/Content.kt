package com.example.musinsa.domain

data class Data(
    val header: Header?,
    val footer: Footer?,
    val contents: Content,
) {
    val isFooterShown = run {
        contents.detail.size >= contents.contentSize
    }
}

data class Header(
    val title: String,
    val iconUrl: String?,
    val linkUrl: String?
)

data class Footer(
    val type: FooterType,
    val title: String,
    val url: String?,
)

enum class FooterType {
    REFRESH,
    MORE
}
enum class ContentType {
    BANNER,
    GRID,
    SCROLL,
    STYLE
}

data class Content(
    val type: ContentType,
    val detail: List<ContentDetail>,
    val contentSize: Int = 6,
) {
    val filteredDetail: List<ContentDetail> = run {
        when (type) {
            ContentType.STYLE,
                ContentType.GRID -> {
                    if (contentSize >= detail.size) {
                        detail
                    } else {
                        detail.subList(0, contentSize)
                    }
                }
            else -> {
                detail
            }
        }
    }
}

sealed interface ContentDetail {
    data class Goods(
        val linkUrl: String,
        val thumbnailUrl: String,
        val brandName: String,
        val price: Int,
        val saleRate: Int,
        val hasCoupon: Boolean) : ContentDetail

    data class Style(
        val linkUrl: String,
        val thumbnailUrl: String
    ): ContentDetail

    data class Banner(
        val linkUrl: String,
        val thumbnailUrl: String,
        val title: String,
        val description: String,
        val keyword: String
    ): ContentDetail
}