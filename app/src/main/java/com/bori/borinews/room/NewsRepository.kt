package com.bori.borinews.room

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData

class NewsRepository
{
    companion object
    {
        private class insertAsyncTask(private val asyncTaskDao: NewsDao):
            AsyncTask<NewsEntity, Void, Void>()
        {
            override fun doInBackground(vararg params: NewsEntity): Void?
            {
                asyncTaskDao.insert(params[0])
                return null
            }

        }
    }

    private val newsDao: NewsDao
    private val allNews: LiveData<List<NewsEntity>>

    constructor(app: Application)
    {
        val db = NewsDatabase.getNewsDatabase(app)
        newsDao = db.newsDao()
        allNews = newsDao.getAllNews()
    }

    fun getAllNews(): LiveData<List<NewsEntity>>
    {
        return allNews
    }

    fun insert(newsEntity: NewsEntity)
    {
        insertAsyncTask(newsDao).execute(newsEntity)
    }

}