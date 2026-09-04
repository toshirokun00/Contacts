package com.comtrade.data.datasource

import androidx.paging.PagingSource
import com.comtrade.data.room.ContactEntity

interface LocalDataSource {

    fun getContacts(): PagingSource<Int, ContactEntity>

}