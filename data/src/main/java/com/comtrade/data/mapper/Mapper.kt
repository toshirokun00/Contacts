package com.comtrade.data.mapper

import com.comtrade.data.response.ContactResponseModel
import com.comtrade.data.room.ContactEntity
import com.comtrade.domain.model.Contact

fun ContactEntity.toDomain() = Contact(
    id = id,
    firstName = firstName,
    lastName = lastName,
    email = email,
    avatarUrl = avatarUrl
)

fun ContactResponseModel.toEntity(page : Int) = ContactEntity(
    id = id,
    firstName = firstName,
    lastName = lastName,
    email = email,
    avatarUrl = avatarUrl,
    page =page

)

