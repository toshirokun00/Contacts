package com.comtrade.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.comtrade.domain.model.Contact
import com.comtrade.domain.usecase.ContactUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ContactsViewModel @Inject constructor(
    private val useCase: ContactUsecase
) : ViewModel() {

    val contact: Flow<PagingData<Contact>> = useCase
        .getContacts()
        .cachedIn(viewModelScope)

}