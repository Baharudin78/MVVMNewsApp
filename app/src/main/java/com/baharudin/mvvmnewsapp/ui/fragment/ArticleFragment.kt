package com.baharudin.mvvmnewsapp.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.baharudin.mvvmnewsapp.R
import com.baharudin.mvvmnewsapp.databinding.FragmentArticleBinding
import com.baharudin.mvvmnewsapp.ui.NewsActivity
import com.baharudin.mvvmnewsapp.ui.NewsViewModel

class ArticleFragment : Fragment(R.layout.fragment_article) {

    private var _binding : FragmentArticleBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel : NewsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {super.onViewCreated(view, savedInstanceState)
        _binding = FragmentArticleBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as NewsActivity).viewModel
    }
}