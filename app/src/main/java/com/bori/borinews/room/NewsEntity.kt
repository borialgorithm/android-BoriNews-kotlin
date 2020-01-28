package com.bori.borinews.room

import android.graphics.drawable.Drawable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news_table")
data class NewsEntity
    (
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    val id: Int,
    @NonNull
    @ColumnInfo(name = "news_type") val type: String,
    @ColumnInfo(name = "news_title") val title: String?,
    @ColumnInfo(name = "news_link") val link: String?,
    @ColumnInfo(name = "news_src") val source: String?,
    @ColumnInfo(name = "news_src_logo_url")val srcLogo: String?,
    @ColumnInfo(name = "news_img") val newsImg: String?,
    @ColumnInfo(name = "news_src_url") val sourceUrl: String?,
    @ColumnInfo(name = "news_date") val date: String?,
    @ColumnInfo(name = "news_category") val catetory: String?,
    @NonNull
    @ColumnInfo(name = "sns_content") val snsContent: String,
    @ColumnInfo(name = "src_string") val strString: String?,
    @ColumnInfo(name = "sim_level") val similarityLevel: String?

)
