package com.comtrade.data.datasource

import com.comtrade.data.response.ContactResponse

interface RemoteDataSource {

    suspend fun getContacts(page: Int) : Result<ContactResponse>

}