package com.comtrade.contactlistapp.di

import com.comtrade.data.datasource.LocalDataSource
import com.comtrade.data.datasource.RemoteDataSource
import com.comtrade.data.impl.LocalDataSourceImpl
import com.comtrade.data.impl.RemoteDataSourceImpl
import com.comtrade.data.impl.RepositoryImpl
import com.comtrade.domain.repository.ContactRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindContactRepository(impl : RepositoryImpl) : ContactRepository

}
