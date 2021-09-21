package com.newsapp.ui.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.newsapp.R
import com.newsapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
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
        observeForNoNews()
         search()
        return binding.root
    }
      private fun search(){
     binding.searchEd.addTextChangedListener(object : TextWatcher {
         override fun afterTextChanged(s: Editable?) {
             viewModel.filter(s.toString())
         }

         override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
         }

         override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
         }
     })
 }
    private fun observeNavigation() {
        viewModel.navigate.observe(viewLifecycleOwner) {
            it?.let {
                Log.i("TAG", "observeNavigation: $it")
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToArticleViewFragment(it))
            viewModel.completeNavigation()
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
    private fun observeForNoNews() {
        viewModel.noNews.observe(viewLifecycleOwner, Observer {
            if (it) {
                binding.noNews.visibility = View.VISIBLE
            } else {
                binding.noNews.visibility = View.GONE

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