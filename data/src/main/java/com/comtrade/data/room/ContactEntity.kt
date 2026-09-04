package com.comtrade.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts")
data class ContactEntity(
    @PrimaryKey val id: Int ,
    val firstName : String ?=  null,
    val lastName : String ?= null,
    val email : String ?= null,
    val avatarUrl : String ?= null,
    val page: Int
)
