package com.baharudin.mvvmnewsapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.baharudin.mvvmnewsapp.R
import com.baharudin.mvvmnewsapp.databinding.ActivityNewsBinding
import com.baharudin.mvvmnewsapp.db.ArticleDatabase
import com.baharudin.mvvmnewsapp.repository.NewsRepository

class NewsActivity : AppCompatActivity() {

    private lateinit var binding : ActivityNewsBinding
    private lateinit var navController: NavController
    lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val newsRepository = NewsRepository(ArticleDatabase(this))
        val viewModelProviderFactory = NewsViewModelProviderFactory(newsRepository)
        viewModel = ViewModelProvider(this,viewModelProviderFactory).get(NewsViewModel::class.java)

        val navigationFragment = supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        navController = navigationFragment.findNavController()

        binding.apply {
            bottomNavigationView2.setupWithNavController(navController)
        }
    }
}