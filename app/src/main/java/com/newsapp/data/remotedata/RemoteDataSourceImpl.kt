package com.newsapp.data.remotedata

import com.newsapp.model.NewsResponse
import java.lang.Exception
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(val retrofitService: RetrofitService):RemoteDataSource {
    override suspend fun getNews(): NewsResponse? {
        val result=retrofitService.getNews()
        if (result.isSuccessful){
            return result.body()
        }else{
            throw Exception(result.message())
        }
    }
}