package com.bori.borinews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bori.borinews.ui.recommend.RcmdNewsAdapter
import com.bori.borinews.ui.recommend.RecommendNewsFragment
import com.bori.borinews.viewmodel.RecommendNewsViewModel
import kotlinx.android.synthetic.main.fragment_rcmd.*

class MainActivity : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null)
        {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, RecommendNewsFragment.newInstance())
                .commitNow()
        }

    }


}
