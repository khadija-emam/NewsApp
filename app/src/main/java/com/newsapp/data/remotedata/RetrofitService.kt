package com.newsapp.data.remotedata

import com.newsapp.di.API_KEY
import com.newsapp.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("v2/top-headlines?country=eg&apiKey=63b1f94dad044add871d1e319c630265")
   suspend fun getNews(@Query("country") country:String = "eg",
    @Query("apiKey")apiKey:String= API_KEY):Response<NewsResponse>

}