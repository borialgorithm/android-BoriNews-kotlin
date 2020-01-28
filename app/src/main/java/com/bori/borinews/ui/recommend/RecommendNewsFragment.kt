package com.bori.borinews.ui.recommend

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bori.borinews.R
import com.bori.borinews.databinding.FragmentRcmdBinding
import com.bori.borinews.room.NewsEntity
import com.bori.borinews.viewmodel.RecommendNewsViewModel
import kotlinx.android.synthetic.main.fragment_rcmd.*

class RecommendNewsFragment : Fragment()
{

    companion object
    {
        fun newInstance() = RecommendNewsFragment()
    }

    private lateinit var viewModel: RecommendNewsViewModel
    private lateinit var binding: FragmentRcmdBinding

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,
                               savedInstanceState: Bundle?): View
    {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_rcmd,container,false)
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
        //return inflater.inflate(R.layout.fragment_rcmd, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(RecommendNewsViewModel::class.java)
        setRecyclerView()
    }

    private fun setRecyclerView()
    {
        val context: Context = requireContext()

        val adapter = RcmdNewsAdapter(context)
        binding.rcmdRecyclerview.adapter = adapter
        binding.rcmdRecyclerview.layoutManager = LinearLayoutManager(context)
        rcmd_recyclerview.addItemDecoration(
            DividerItemDecoration(context, DividerItemDecoration.VERTICAL)

        )
        binding.rcmdRecyclerview.apply {
           addItemDecoration(DividerItemDecoration(context,DividerItemDecoration.VERTICAL))

        }

        viewModel.getAllNews().observe(viewLifecycleOwner, Observer<List<NewsEntity>> { newsList ->
            adapter.setNewsList(newsList)
        })
    }


}
