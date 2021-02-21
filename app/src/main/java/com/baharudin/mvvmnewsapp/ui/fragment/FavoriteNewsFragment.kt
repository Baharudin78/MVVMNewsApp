package com.baharudin.mvvmnewsapp.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.baharudin.mvvmnewsapp.R
import com.baharudin.mvvmnewsapp.databinding.FragmentFavoriteNewsBinding
import com.baharudin.mvvmnewsapp.ui.NewsActivity
import com.baharudin.mvvmnewsapp.ui.NewsViewModel

class FavoriteNewsFragment : Fragment(R.layout.fragment_favorite_news) {

    private var _binding : FragmentFavoriteNewsBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel : NewsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentFavoriteNewsBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as NewsActivity).viewModel
    }
}