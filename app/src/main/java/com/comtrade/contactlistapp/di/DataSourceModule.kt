package com.comtrade.contactlistapp.di

import com.comtrade.data.datasource.LocalDataSource
import com.comtrade.data.datasource.RemoteDataSource
import com.comtrade.data.impl.LocalDataSourceImpl
import com.comtrade.data.impl.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun bindRemoteDataSource(impl : RemoteDataSourceImpl) : RemoteDataSource

    @Binds
    @Singleton
    abstract fun bindLocalDataSource(impl: LocalDataSourceImpl) : LocalDataSource
}
