package com.bori.borinews.retrofit

import retrofit2.http.GET

//private  val  LOCAL:/*@@zvczos@@*/kotlin.String? = "http://192.168.0.2:8000/bori"
//val  HEAD_NEWS_URL:/*@@zvczos@@*/kotlin.String? = LOCAL + "/head"
//val  RCMD_NEWS_URL:/*@@zvczos@@*/kotlin.String? = LOCAL + "/rcmd"

interface RcmdNewsService
{
   @GET("/rcmd")
   fun getRcmdNews()
}
