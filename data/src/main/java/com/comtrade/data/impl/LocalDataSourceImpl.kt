package com.comtrade.data.impl

import androidx.paging.PagingSource
import com.comtrade.data.datasource.LocalDataSource
import com.comtrade.data.room.ContactDao
import com.comtrade.data.room.ContactEntity
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val dao: ContactDao
) : LocalDataSource {

    override fun getContacts(): PagingSource<Int, ContactEntity> {
        return dao.getContactList()
    }
}