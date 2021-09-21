package com.newsapp.ui.articleview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.newsapp.R
import com.newsapp.databinding.ArticleViewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleViewFragment:Fragment() {
    lateinit var binding: ArticleViewBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.article_view, container, false)
        binding.lifecycleOwner = this
        val args=ArticleViewFragmentArgs.fromBundle(requireArguments())
        setArticle(args)

        return binding.root
    }
    private fun setArticle( args: ArticleViewFragmentArgs) {
        binding.title.text = args.article.title
        binding.sourceName.text = args.article.source?.name
        binding.description.text = args.article.description
        binding.img=args.article.urlToImage
    }
}