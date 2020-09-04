package com.example.newsapp.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.adapters.NewsAdapter
import com.example.newsapp.repository.NewsRepository
import com.example.newsapp.ui.MainActivity
import com.example.newsapp.ui.NewsViewModel
import com.example.newsapp.utils.Resource
import kotlinx.android.synthetic.main.fragment_breaking_new.*
val TAG = "BreakingNews Fragment"

class BreakingNewFragment : Fragment() {

    lateinit var viewModel: NewsViewModel

    lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_breaking_new, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       viewModel = ViewModelProvider(requireActivity()).get(NewsViewModel::class.java)


        setUpRecyclerView()

        /**subscribe to liveData changes**/
        viewModel.breakingNews.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    /**check if data !=null**/
                    response.data?.let { newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles)

                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e(TAG, "onViewCreated: $message")

                    }
                }
                is Resource.Loading ->{
                    showProgressBar()
                }
            }
        })
    }

    private fun hideProgressBar(){
        paginationProgressBar.visibility =View.GONE
    }
    private fun showProgressBar(){
        paginationProgressBar.visibility =View.VISIBLE
    }

    /**setup recycler view*/
    private fun setUpRecyclerView(){
        newsAdapter = NewsAdapter()
        rvBreakingNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

}