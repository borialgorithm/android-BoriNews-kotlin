package com.bori.borinews.retrofit

import com.bori.borinews.news.HeadNewsModel
import com.bori.borinews.news.RcmdNewsModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

//private  val  LOCAL:/*@@zvczos@@*/kotlin.String? = "http://192.168.0.2:8000/bori"
//val  HEAD_NEWS_URL:/*@@zvczos@@*/kotlin.String? = LOCAL + "/head"
//val  RCMD_NEWS_URL:/*@@zvczos@@*/kotlin.String? = LOCAL + "/rcmd"

interface RetrofitService
{
   @GET("/rcmd")
   fun getRcmdNews(@Query("id") id: String): Call<RcmdNewsModel>

   @GET("bori/head")
   fun getHeadNews(): Call<HeadNewsModel>

}
