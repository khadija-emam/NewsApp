package com.newsapp.data.repository

import com.newsapp.data.remotedata.RemoteDataSource
import com.newsapp.model.NewsResponse
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource):Repository {
    override suspend fun getNews(): NewsResponse? {
        return remoteDataSource.getNews()
    }
}