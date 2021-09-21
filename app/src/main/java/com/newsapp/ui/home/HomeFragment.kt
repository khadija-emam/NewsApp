package com.newsapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.newsapp.R
import com.newsapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    val viewModel: HomeViewModel by viewModels()
    lateinit var adapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)


        binding.lifecycleOwner = this

        setUpAdapter()
        observeForMessage()
        observeForVideos()
        observeForLoading()
        observeNavigation()
        return binding.root
    }

    private fun observeNavigation() {
        viewModel.navigate.observe(viewLifecycleOwner) {
            it?.let {
                HomeFragmentDirections.actionHomeFragmentToDownloadFragment(it)
            }
        }
    }

    private fun observeForLoading() {
        viewModel.progress.observe(viewLifecycleOwner, Observer {
            if (it) {
                binding.loading.visibility = View.VISIBLE
            } else {
                binding.loading.visibility = View.GONE

            }
        })
    }

    private fun observeForVideos() {
        viewModel.getPosts()
        viewModel.videosList.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }

    private fun observeForMessage() {
        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })
    }

    private fun setUpAdapter() {
        adapter = NewsAdapter(NewsClickListener { viewModel.onItemClicked(it) })
        binding.resultsRv.adapter = adapter
    }
}