package com.bori.borinews.ui.recommend

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bori.borinews.R
import com.bori.borinews.databinding.RcmdNewsCardHeaderBinding
import com.bori.borinews.room.NewsEntity

class RcmdNewsAdapter internal constructor (context: Context):
    RecyclerView.Adapter<RcmdNewsAdapter.NewsViewHolder>()
{
    private var newsList: List<NewsEntity>? = null

    inner class NewsViewHolder(private val binding:RcmdNewsCardHeaderBinding):
        RecyclerView.ViewHolder(binding.root)
    {
        fun bind(news: NewsEntity)
        {
            binding.news = news
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder
    {
        val binding: RcmdNewsCardHeaderBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.rcmd_news_card_header,
            parent,
            false )

        return NewsViewHolder(binding)
    }

    override fun getItemCount(): Int
    {
       return if(newsList != null)
           newsList!!.size
        else
           0
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int)
    {
        val news: NewsEntity? = newsList?.get(position)
        if (news != null)
        {
            holder.bind(news)
        }
    }

    internal fun setNewsList(newsList: List<NewsEntity>)
    {
        this.newsList = newsList
        notifyDataSetChanged()
    }

}