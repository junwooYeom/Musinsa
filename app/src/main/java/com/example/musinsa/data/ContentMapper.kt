package com.example.musinsa.data

import com.example.musinsa.domain.Content
import com.example.musinsa.domain.ContentDetail
import com.example.musinsa.domain.ContentType
import com.example.musinsa.domain.Data
import com.example.musinsa.domain.Footer
import com.example.musinsa.domain.FooterType
import com.example.musinsa.domain.Header

fun DataDto.toData(): Data = Data(
    header = header?.toHeader(),
    footer = footer?.toFooter(),
    contents = content.toContent()
)

private fun HeaderDto.toHeader(): Header = Header(
    title = title,
    iconUrl = iconUrl,
    linkUrl = linkUrl
)

private fun FooterDto.toFooter(): Footer = Footer(
    type = FooterType.valueOf(type),
    title = title,
    url = iconUrl
)

private fun ContentsDto.toContent(): Content {
    val currentType = ContentType.valueOf(type)
    return Content(
        type = currentType,
        detail = when (currentType) {
            ContentType.BANNER -> banners?.map {
                it.toContentDetail()
            } ?: emptyList()
            ContentType.GRID, ContentType.SCROLL -> goods?.map {
                it.toContentDetail()
            } ?: emptyList()
            ContentType.STYLE -> styles?.map {
                it.toContentDetail()
            } ?: emptyList()
        }
    )
}

private fun ContentsDto.BannerDto.toContentDetail(): ContentDetail =
    ContentDetail.Banner(
        linkUrl = linkUrl,
        thumbnailUrl = thumbnailUrl,
        title = title,
        description = description,
        keyword = keyword
    )

private fun ContentsDto.GoodsDto.toContentDetail(): ContentDetail =
    ContentDetail.Goods(
        linkUrl = linkUrl,
        thumbnailUrl = thumbnailUrl,
        brandName = brandName,
        price = price,
        saleRate = saleRate,
        hasCoupon = hasCoupon
    )

private fun ContentsDto.StyleDto.toContentDetail(): ContentDetail =
    ContentDetail.Style(
        linkUrl = linkUrl,
        thumbnailUrl = thumbnailUrl
    )