package com.comtrade.contactlistapp.di

import android.content.Context
import androidx.room.Room
import com.comtrade.data.room.ContactDatabase
import com.comtrade.data.room.ContactDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): ContactDatabase =
        Room.databaseBuilder(context, ContactDatabase::class.java, "contacts.db").build()

    @Provides
    @Singleton
    fun provideContactDao(db: ContactDatabase): ContactDao = db.contactDao()
}