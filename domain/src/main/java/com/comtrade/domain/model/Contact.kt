package com.comtrade.domain.model

data class Contact(
    val id: Int ,
    val email : String ?= null,
    val firstName: String  ?= null,
    val lastName : String  ?= null,
    val avatarUrl : String  ?= null,
)
