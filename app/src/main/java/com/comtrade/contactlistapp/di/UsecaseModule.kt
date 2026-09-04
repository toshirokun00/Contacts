package com.comtrade.contactlistapp.di

import com.comtrade.domain.repository.ContactRepository
import com.comtrade.domain.usecase.ContactUsecase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun provideGetContactsUseCase(repository: ContactRepository): ContactUsecase =
        ContactUsecase(repository)
}