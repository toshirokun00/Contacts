package com.comtrade.data.impl

import com.comtrade.data.ApiService
import com.comtrade.data.datasource.RemoteDataSource
import com.comtrade.data.response.ContactResponse
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService,
) : RemoteDataSource {

    override suspend fun getContacts(page: Int): Result<ContactResponse> {
        return try {
            val response = apiService.getContacts(page)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}