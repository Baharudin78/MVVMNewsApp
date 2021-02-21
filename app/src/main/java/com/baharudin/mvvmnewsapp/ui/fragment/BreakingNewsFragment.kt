package com.baharudin.mvvmnewsapp.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.baharudin.mvvmnewsapp.R
import com.baharudin.mvvmnewsapp.databinding.FragmentBreakingNewsBinding

class BreakingNewsFragment : Fragment(R.layout.fragment_breaking_news) {

    private var _binding : FragmentBreakingNewsBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentBreakingNewsBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
    }
}