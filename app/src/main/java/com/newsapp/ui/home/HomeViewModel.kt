package com.newsapp.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.newsapp.data.repository.Repository
import com.newsapp.model.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val repository: Repository) : ViewModel() {
    private var _response = listOf<Article>()

    private val _videosList = MutableLiveData<List<Article>>()
    val videosList: LiveData<List<Article>>
        get() = _videosList

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage
    private val _noNews = MutableLiveData<Boolean>()
    val noNews: LiveData<Boolean>
        get() = _noNews
    private val _progress = MutableLiveData<Boolean>()
    val progress: LiveData<Boolean>
        get() = _progress
    private val _navigate = MutableLiveData<Article>()
    val navigate: LiveData<Article>
        get() = _navigate

    fun filter(searchWord: String) {
        if (searchWord.isNotEmpty()) {
            val filteredItem = _videosList.value?.filter { product ->
                product.title.contains(searchWord)
            }
            _videosList.value = filteredItem!!
        } else {
            _videosList.value = _response
        }
    }

    fun getPosts() {
     viewModelScope.launch {
         _progress.value=true
         val result=repository.getNews()
         try {
             if (!result?.articles.isNullOrEmpty()){
                 _progress.value=false
                 _noNews.value=false
                 if (result != null) {
                     _response=result.articles
                 }
                 _videosList.value=result?.articles!!
             }else{
                 _noNews.value=true
                 _progress.value=false
             }
         }catch (e:Exception){
             _noNews.value=false

             _progress.value=false
             _errorMessage.value=e.message
         }
     }
    }
    fun onItemClicked(article: Article) {
        _navigate.value = article

    }


  fun completeNavigation(){
      _navigate.value=null

  }

}