package com.newsapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.newsapp.databinding.NewsItemBinding
import com.newsapp.model.Article

class NewsAdapter (val onClickListener: NewsClickListener) : ListAdapter<Article, NewsAdapter.ArticleViewHolder>(NewsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = getItem(position)
        holder.bind(article)
        holder.itemView.setOnClickListener { article?.let { onClickListener.onClick(it) } }


    }

    class ArticleViewHolder private constructor(val binding: NewsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            binding.article = article
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ArticleViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = NewsItemBinding.inflate(layoutInflater, parent, false)
                return ArticleViewHolder(binding)
            }
        }
    }
}


class NewsDiffCallback : DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.url == newItem.url
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }
}

class NewsClickListener(val onClick: (video:Article) -> Unit)
