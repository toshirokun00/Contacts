package com.comtrade.domain.usecase

import androidx.paging.PagingData
import com.comtrade.domain.model.Contact
import com.comtrade.domain.repository.ContactRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ContactUsecase @Inject constructor(
    private val repository: ContactRepository
) {
    fun getContacts(): Flow<PagingData<Contact>> {
        return repository.getContactList()
    }

}