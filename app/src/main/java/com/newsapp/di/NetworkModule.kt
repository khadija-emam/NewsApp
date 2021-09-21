package com.newsapp.di

import com.newsapp.data.remotedata.RetrofitService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Movie API communication setup via Retrofit.
 */
 const val API_KEY = "63b1f94dad044add871d1e319c630265"
private const val NEWS_BASE_URL = "https://newsapi.org/"

// Install this module in Hilt-generated SingletonComponent
@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {


    // Makes Hilt provide Retrofit instance when a Retrofit type is requested
    @Provides
    @Singleton
    fun providesRetrofit(): RetrofitService {
        // Configure retrofit to parse JSON and use coroutines
        val retrofit = Retrofit.Builder()
            .baseUrl(NEWS_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(RetrofitService::class.java)
    }
}