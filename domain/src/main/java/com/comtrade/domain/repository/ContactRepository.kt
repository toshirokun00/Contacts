package com.comtrade.domain.repository

import androidx.paging.PagingData
import com.comtrade.domain.model.Contact
import kotlinx.coroutines.flow.Flow

interface ContactRepository {

    fun getContactList() : Flow<PagingData<Contact>>

}