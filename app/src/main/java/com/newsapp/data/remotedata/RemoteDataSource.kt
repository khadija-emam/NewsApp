package com.newsapp.data.remotedata

import com.newsapp.model.NewsResponse

interface RemoteDataSource {
suspend fun getNews(): NewsResponse?
}