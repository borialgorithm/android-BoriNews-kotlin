package com.bori.borinews.retrofit

import android.util.Log
import com.bori.borinews.news.RcmdNewsModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrotifManager private constructor()
{
    companion object
    {
        @Volatile private var INSTANCE: RetrotifManager? = null
        fun getRetrofitManager(): RetrotifManager?
        {
            if(INSTANCE == null)
            {
                synchronized(this)
                {
                    INSTANCE = RetrotifManager()
                }
            }

            return INSTANCE
        }
    }

    private var server: RetrofitService? = null
    private val baseUrl: String = "https://c07db832.ngrok.io"

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    init
    {
        server = retrofit.create(RetrofitService::class.java)
    }


    fun getRcmdNews()
    {
        server?.getRcmdNews("bloodwave")?.enqueue(object : Callback<RcmdNewsModel>
        {

            override fun onFailure(call: Call<RcmdNewsModel>, t: Throwable)
            {

                Log.e("fail", "failllllll")
            }

            override fun onResponse(call: Call<RcmdNewsModel>, response: Response<RcmdNewsModel>)
            {

                Log.d("nice!!", "niceeeeee")
            }

        })

    }
}