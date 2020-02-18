package com.bori.borinews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bori.borinews.auth.SessionManager
import com.bori.borinews.news.HeadNewsModel
import com.bori.borinews.news.RcmdNewsModel
import com.bori.borinews.retrofit.RetrofitService
import com.bori.borinews.ui.recommend.RcmdNewsAdapter
import com.bori.borinews.ui.recommend.RecommendNewsFragment
import com.bori.borinews.viewmodel.RecommendNewsViewModel
import kotlinx.android.synthetic.main.fragment_rcmd.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity()
{
    private var sessionManager: SessionManager? = null

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        checkAuth()

        setContentView(R.layout.main_activity)
        if (savedInstanceState == null)
        {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, RecommendNewsFragment.newInstance())
                .commitNow()
        }
    }

    private fun checkAuth()
    {
        sessionManager = SessionManager.getInstance(this)
        sessionManager?.twitterSettingInit()
        sessionManager?.twitterLogin()
    }


}
