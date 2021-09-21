package com.newsapp.data.repository

import NewsResponse

interface Repository {
    suspend fun getNews():NewsResponse?
}