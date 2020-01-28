package com.bori.borinews.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NewsDao
{
    @Query("SELECT * FROM news_table")
    fun getAllNews() : LiveData<List<NewsEntity>>

    @Insert
    fun insert(news: NewsEntity)

    @Query("DELETE FROM news_table")
    fun deleteAllNews()

}