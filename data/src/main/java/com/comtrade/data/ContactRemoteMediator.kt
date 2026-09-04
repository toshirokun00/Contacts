package com.comtrade.data

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.comtrade.data.datasource.RemoteDataSource
import com.comtrade.data.mapper.toEntity
import com.comtrade.data.room.ContactDatabase
import com.comtrade.data.room.ContactEntity
import com.comtrade.data.room.RemoteKeyEntity

@OptIn(ExperimentalPagingApi::class)
class ContactRemoteMediator(
    private val remote: RemoteDataSource,
    private val contactDatabase: ContactDatabase
) : RemoteMediator<Int, ContactEntity>() {

    override suspend fun initialize(): InitializeAction {
        val count = contactDatabase.contactDao().getCount()
        return if (count > 0) {
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ContactEntity>
    ): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> {
                    1
                }
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val remoteKey = contactDatabase.remoteKeyDao().getKey()
                    remoteKey?.nextPage ?: return MediatorResult.Success(endOfPaginationReached = true)
                }
            }

            Log.d("Mediator", "fetching page=$page")
            val response = remote.getContacts(page)

            if (response.isFailure) {
                return MediatorResult.Error(
                    response.exceptionOrNull() ?: Exception("Unknown error")
                )
            }

            val contactResponse = response.getOrNull()
            val contacts = contactResponse?.data
                ?.map { it.toEntity(page) }
                ?: emptyList()

            val isLastPage = contactResponse?.page == contactResponse?.totalPage
            val nextPage = if (isLastPage) null else page + 1

            contactDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    contactDatabase.contactDao().clearAll()
                    contactDatabase.remoteKeyDao().clearAll()
                }
                contactDatabase.remoteKeyDao().upsert(RemoteKeyEntity(nextPage = nextPage))
                contactDatabase.contactDao().saveContact(contacts)
            }

            MediatorResult.Success(endOfPaginationReached = nextPage == null)

        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}