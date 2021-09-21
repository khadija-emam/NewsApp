package com.newsapp.data.repository

import com.newsapp.model.NewsResponse

interface Repository {
    suspend fun getNews(): NewsResponse?
}