package com.baharudin.mvvmnewsapp.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.baharudin.mvvmnewsapp.R
import com.baharudin.mvvmnewsapp.adapter.ArticleAdapter
import com.baharudin.mvvmnewsapp.databinding.FragmentBreakingNewsBinding
import com.baharudin.mvvmnewsapp.ui.NewsActivity
import com.baharudin.mvvmnewsapp.ui.NewsViewModel
import com.baharudin.mvvmnewsapp.util.Resource

class BreakingNewsFragment : Fragment(R.layout.fragment_breaking_news) {

    private var _binding : FragmentBreakingNewsBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel : NewsViewModel
    lateinit var newsAdapter : ArticleAdapter

    private var TAG ="BreakingNewsFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentBreakingNewsBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
        setupRecycleview()

        viewModel = (activity as NewsActivity).viewModel

        viewModel.breakingNews.observe(viewLifecycleOwner, Observer { response ->
            when(response){
                is Resource.Success -> {
                    hideProgeressbar()
                    response.data?.let { newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles)
                    }
                }
                is Resource.Error -> {
                    hideProgeressbar()
                    response.messege?.let {messege ->
                        Log.e(TAG,"terjadi error di : $messege")
                    }
                }
                is Resource.Loading -> {
                    showProgressbar()
                }
            }
        })
    }

    private fun hideProgeressbar (){
        binding.paginationProgressBar.visibility = View.INVISIBLE
    }
    private fun showProgressbar(){
        binding.paginationProgressBar.visibility = View.VISIBLE
    }

    private fun setupRecycleview(){
        newsAdapter = ArticleAdapter()
        binding.rvBreakingNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}