package com.comtrade.data.impl

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.comtrade.data.ContactRemoteMediator
import com.comtrade.data.datasource.LocalDataSource
import com.comtrade.data.datasource.RemoteDataSource
import com.comtrade.data.mapper.toDomain
import com.comtrade.data.room.ContactDatabase
import com.comtrade.domain.model.Contact
import com.comtrade.domain.repository.ContactRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val remote: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val contactDatabase: ContactDatabase
) : ContactRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getContactList(): Flow<PagingData<Contact>> {
        val pagingSourceFactory = { localDataSource.getContacts() }

        return Pager(
            config = PagingConfig(
                pageSize = 6,
                initialLoadSize = 6,
                enablePlaceholders = false,
                prefetchDistance = 1
            ),
            remoteMediator = ContactRemoteMediator(remote, contactDatabase),
            pagingSourceFactory = pagingSourceFactory
        ).flow.map { pagingData ->
            pagingData.map { entity -> entity.toDomain() }
        }
    }
}