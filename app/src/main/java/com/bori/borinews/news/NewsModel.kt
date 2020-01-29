package com.bori.borinews.news

import com.google.gson.annotations.SerializedName


class RcmdNewsModel (
    val id: Int,
    @SerializedName("news_type")
    val type: String,
    val title: String?,
    val link: String?,
    val source: String?,
    val srcLogo: String?,
    val newsImg: String?,
    val sourceUrl: String?,
    val date: String?,
    val catetory: String?,
    val snsContent: String,
    val strString: String?,
    val similarityLevel: String?
)

class HeadNewsModel(
    @SerializedName("news_type")
    val newsType: String,
    @SerializedName("news_list")
    val headNewsList: List<String>
)


