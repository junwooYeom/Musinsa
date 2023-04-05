package com.example.musinsa.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ContentResponse(
    @SerializedName("data")
    val data: List<DataDto>
)

data class DataDto(
    @Expose
    @SerializedName("header")
    val header: HeaderDto?,
    @SerializedName("contents")
    val content: ContentsDto,
    @Expose
    @SerializedName("footer")
    val footer: FooterDto?
)

data class HeaderDto(
    @SerializedName("title")
    val title: String,
    @Expose
    @SerializedName("iconURL")
    val iconUrl: String?,
    @Expose
    @SerializedName("linkURL")
    val linkUrl: String?
)

data class FooterDto(
    @SerializedName("type")
    val type: String,
    @SerializedName("title")
    val title: String,
    @Expose
    @SerializedName("iconURL")
    val iconUrl: String?
)

data class ContentsDto(
    @SerializedName("type")
    val type: String,
    @Expose
    @SerializedName("banners")
    val banners: List<BannerDto>?,
    @Expose
    @SerializedName("goods")
    val goods: List<GoodsDto>?,
    @Expose
    @SerializedName("styles")
    val styles: List<StyleDto>?
) {
    data class BannerDto(
        @SerializedName("linkURL")
        val linkUrl: String,
        @SerializedName("thumbnailURL")
        val thumbnailUrl: String,
        @SerializedName("title")
        val title: String,
        @SerializedName("description")
        val description: String,
        @SerializedName("keyword")
        val keyword: String
    )

    data class GoodsDto(
        @SerializedName("linkURL")
        val linkUrl: String,
        @SerializedName("thumbnailURL")
        val thumbnailUrl: String,
        @SerializedName("brandName")
        val brandName: String,
        @SerializedName("price")
        val price: Int,
        @SerializedName("saleRate")
        val saleRate: Int,
        @SerializedName("hasCoupon")
        val hasCoupon: Boolean
    )

    data class StyleDto(
        @SerializedName("linkURL")
        val linkUrl: String,
        @SerializedName("thumbnailURL")
        val thumbnailUrl: String,
    )
}
