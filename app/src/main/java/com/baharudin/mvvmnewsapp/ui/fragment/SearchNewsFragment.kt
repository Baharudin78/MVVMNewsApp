package com.baharudin.mvvmnewsapp.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.baharudin.mvvmnewsapp.R
import com.baharudin.mvvmnewsapp.adapter.ArticleAdapter
import com.baharudin.mvvmnewsapp.databinding.FragmentSearchNewsBinding
import com.baharudin.mvvmnewsapp.ui.NewsActivity
import com.baharudin.mvvmnewsapp.ui.NewsViewModel
import com.baharudin.mvvmnewsapp.util.Constants.Companion.SEARCH_DELAY
import com.baharudin.mvvmnewsapp.util.Resource
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchNewsFragment :Fragment(R.layout.fragment_search_news){

    private var _binding : FragmentSearchNewsBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel : NewsViewModel
    lateinit var articleAdapter: ArticleAdapter
    private var TAG ="SearchNews"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentSearchNewsBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        setupRecycleView()

        var job : Job? = null
        binding.etSearch.addTextChangedListener {editable ->
            job?.cancel()
            job = MainScope().launch {
                delay(SEARCH_DELAY)
                editable?.let {
                    if (editable.toString().isNotEmpty()){
                        viewModel.getSearchNews(editable.toString())
                    }
                }
            }
        }

        viewModel = (activity as NewsActivity).viewModel

        viewModel.searchNews.observe(viewLifecycleOwner, Observer {response ->
            when(response){
                is Resource.Success ->{
                    hideProgressBar()
                    response.data?.let { newsResponse ->
                        articleAdapter.differ.submitList(newsResponse.articles)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.messege?.let {messege ->
                        Log.e(TAG,"error di bagian $messege")
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }

        })
    }

    private fun hideProgressBar(){
        binding.paginationProgressBar.visibility = View.INVISIBLE
    }
    private fun showProgressBar(){
        binding.paginationProgressBar.visibility = View.VISIBLE
    }
    private fun setupRecycleView(){
        binding.rvSearchNews.apply {
            articleAdapter = ArticleAdapter()
            adapter = articleAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}