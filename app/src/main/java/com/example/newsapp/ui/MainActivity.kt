package com.example.newsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.newsapp.R
import com.example.newsapp.db.ArticleDatabase
import com.example.newsapp.repository.NewsRepository
import kotlinx.android.synthetic.main.activity_main.*

lateinit var viewModel : ViewModel
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**setUp navigation**/
        bottomNavigationView.setupWithNavController(newsNavHostFragment.findNavController())

        /**ProviderFactory and viewModel instance**/
        val newsRepository = NewsRepository(ArticleDatabase(this))
        val viewModelProviderFactory = NewsViewModelProviderFactory(newsRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(NewsViewModel::class.java)
    }
}