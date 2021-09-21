package com.newsapp.di


import com.newsapp.data.remotedata.RemoteDataSource
import com.newsapp.data.remotedata.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

// Install this module in Hilt-generated SingletonComponent
@InstallIn(SingletonComponent::class)
@Module
abstract class DataSourceModuleBind {

    @Binds
    abstract fun bindRemoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource
}
