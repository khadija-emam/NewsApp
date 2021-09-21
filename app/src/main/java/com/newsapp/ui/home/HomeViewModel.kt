package com.newsapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.newsapp.data.repository.Repository
import com.newsapp.model.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val repository: Repository) : ViewModel() {
    private val _videosList = MutableLiveData<List<Article>>()
    val videosList: LiveData<List<Article>>
        get() = _videosList

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    private val _progress = MutableLiveData<Boolean>()
    val progress: LiveData<Boolean>
        get() = _progress
    private val _navigate = MutableLiveData<Article>()
    val navigate: LiveData<Article>
        get() = _navigate



    fun getPosts() {

    }
    fun onItemClicked(article: Article) {
        _navigate.value = article
    }




}