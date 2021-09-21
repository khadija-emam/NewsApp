package com.newsapp.data.remotedata

import NewsResponse

interface RemoteDataSource {
suspend fun getNews():NewsResponse?
}