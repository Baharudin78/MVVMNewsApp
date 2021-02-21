package com.baharudin.mvvmnewsapp.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.baharudin.mvvmnewsapp.R
import com.baharudin.mvvmnewsapp.databinding.FragmentSearchNewsBinding

class SearchNewsFragment :Fragment(R.layout.fragment_search_news){
    private var _binding : FragmentSearchNewsBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentSearchNewsBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
    }
}