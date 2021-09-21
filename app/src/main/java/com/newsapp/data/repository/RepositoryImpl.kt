package com.newsapp.data.repository

import NewsResponse
import com.newsapp.data.remotedata.RemoteDataSource
import com.newsapp.data.remotedata.RetrofitService
import javax.inject.Inject

class RepositoryImpl @Inject constructor(val remoteDataSource: RemoteDataSource):Repository {
    override suspend fun getNews(): NewsResponse? {
        return remoteDataSource.getNews()
    }
}