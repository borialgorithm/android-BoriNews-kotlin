package com.bori.borinews.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [NewsEntity::class], version = 1)
abstract class NewsDatabase : RoomDatabase()
{
    companion object
    {
        @Volatile private var INSTANCE: NewsDatabase? = null

        fun getNewsDatabase(context: Context): NewsDatabase
        {
            if(INSTANCE == null)
            {
                synchronized(this)
                {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        NewsDatabase::class.java, "news_database"
                    ).build()
                }
            }
            return INSTANCE!!
        }

    }
    abstract fun newsDao(): NewsDao
}