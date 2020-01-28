package com.bori.borinews.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.bori.borinews.room.NewsEntity
import com.bori.borinews.room.NewsRepository

class RecommendNewsViewModel : AndroidViewModel
{
    private val repository: NewsRepository
    private val allNews: LiveData<List<NewsEntity>>

    constructor(app: Application): super(app)
    {
        repository = NewsRepository(app)
        allNews = repository.getAllNews()
    }

    fun getAllNews(): LiveData<List<NewsEntity>> = allNews

    fun insert(newsEntity: NewsEntity)
    {
        repository.insert(newsEntity)
    }
}
