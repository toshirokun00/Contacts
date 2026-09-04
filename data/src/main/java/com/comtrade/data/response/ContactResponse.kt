package com.comtrade.data.response

import com.google.gson.annotations.SerializedName

data class ContactResponse(
    val page: Int,
    @SerializedName("per_page")
    val perPage :Int,
    val total :Int,
    @SerializedName("total_pages")
    val totalPage: Int,
    val data: List<ContactResponseModel>,
)