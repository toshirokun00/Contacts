package com.comtrade.data

import com.comtrade.data.response.ContactResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {
    @GET("api/users")
    suspend fun getContacts(@Query("page")page: Int): ContactResponse
}
