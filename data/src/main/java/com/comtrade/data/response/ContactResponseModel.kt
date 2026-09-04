package com.comtrade.data.response

import com.google.gson.annotations.SerializedName

data class ContactResponseModel(
    val id: Int,
    val email: String? = null,
    @SerializedName("first_name")
    val firstName: String? = null,
    @SerializedName("last_name")
    val lastName: String? = null,
    @SerializedName("avatar")
    val avatarUrl: String? = null,
)