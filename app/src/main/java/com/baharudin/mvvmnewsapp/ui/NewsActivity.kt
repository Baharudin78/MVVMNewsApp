package com.baharudin.mvvmnewsapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.baharudin.mvvmnewsapp.R
import com.baharudin.mvvmnewsapp.databinding.ActivityNewsBinding

class NewsActivity : AppCompatActivity() {

    private lateinit var binding : ActivityNewsBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navigationFragment = supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        navController = navigationFragment.findNavController()

        binding.apply {
            bottomNavigationView2.setupWithNavController(navController)
        }
    }
}